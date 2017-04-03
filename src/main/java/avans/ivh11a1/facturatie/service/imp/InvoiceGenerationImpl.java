package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.administration.InsuranceCompany;
import avans.ivh11a1.facturatie.domain.billing.Declaration;
import avans.ivh11a1.facturatie.domain.billing.Invoice;
import avans.ivh11a1.facturatie.domain.billing.PaymentCondition;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.domain.insurances.Policy;
import avans.ivh11a1.facturatie.service.BillingService;
import avans.ivh11a1.facturatie.service.CustomerService;
import avans.ivh11a1.facturatie.service.InsuranceService;
import avans.ivh11a1.facturatie.service.InvoiceGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by kevin on 28-3-2017.
 */
@Service("InvoiceGeneration")
@Repository
@Transactional(rollbackFor = StateException.class)
public class InvoiceGenerationImpl implements InvoiceGeneration {

    private final BillingService billingService;
    private final InsuranceService insuranceService;
    private final CustomerService customerService;

    @Autowired
    public InvoiceGenerationImpl(BillingService billingService, InsuranceService insuranceService, CustomerService customerService) {
        this.billingService = billingService;
        this.insuranceService = insuranceService;
        this.customerService = customerService;
    }

    @Override
    public Map<String, Object> generateInvoice(Invoice invoice, Map<String, Object> model) {
        //Find the customer given
        Customer customer = customerService.findByCsn(invoice.getCustomer().getCsn());

        //Find the open decelerations
        Iterable<Declaration> decelerations = billingService.findOpenDeclarations(customer);

        //Find decelerations count already on invoice
        int coveredDecelerations = (int) billingService.findClosedDeclarations(customer).spliterator().getExactSizeIfKnown();

        //Find the policy for the customer
        Policy policy = insuranceService.findPolicyByCustomer(customer);

        if (policy != null) {

            //Find the insurance company of the customer
            InsuranceCompany company = policy.getInsurance().getInsuranceCompany();
            //Find the payment condition
            PaymentCondition paymentCondition = billingService.findPaymentConditionById(invoice.getPaymentCondition().getId());

            int needToCover = policy.getInsurance().getCoveredTreatments() - coveredDecelerations;

            float contributionToPay;


            //Create an invoice if there are decelerations
            if (decelerations.iterator().hasNext()) {

                invoice = new Invoice();
                invoice.setCustomer(customer);
                invoice.setPaymentCondition(paymentCondition);
                invoice.setVat(company.getVat());
                invoice.setDateCreated(new Date());
                invoice.setState(1);
                billingService.saveInvoice(invoice);

                for (Declaration d : decelerations) {
                    if (needToCover > 0) {
                        d.setCompensated(d.getPrice());
                        needToCover -= 1;
                    }
                    if (d.getCompensated() <= 0) {
                        contributionToPay = (policy.getContribution() - policy.getContributionUsed());
                        if (contributionToPay <= 0) {
                            d.setCompensated(d.getPrice());
                        } else {
                            if ((contributionToPay - d.getPrice()) < 0) {
                                d.setCompensated(d.getPrice() - contributionToPay);
                                policy.setContributionUsed(policy.getContributionUsed() + contributionToPay);
                            } else {
                                d.setCompensated(0);
                                policy.setContributionUsed(policy.getContributionUsed() + d.getPrice());
                            }
                            insuranceService.savePolicy(policy);
                        }
                    }
                    d.setInvoice(invoice);
                    billingService.saveDeclaration(d);
                }
            } else {
                model.put("failure", "No declarations for this customer at this moment");
            }
        } else {
            model.put("failure", "No policy for this customer at this moment");
        }
        return model;
    }

    @Override
    public Map<String, Object> printInvoice(int invoiceId, Map<String, Object> model) {
        //Find all the needed info to print the invoice
        Invoice invoice = billingService.findInvoiceById(invoiceId);
        Customer customer = invoice.getCustomer();
        Policy policy = insuranceService.findPolicyByCustomer(customer);
        InsuranceCompany company = policy.getInsurance().getInsuranceCompany();
        Iterable<Declaration> declarations = billingService.findDeclarationByCustomer(customer);
        if (declarations == null) {
            model.put("Message", "There was an error printing the invoice");
            return model;
        }

        //Add the general info to the domain
        model.put("Invoice", invoice);
        model.put("Customer", customer);
        model.put("Company", company);
        model.put("Declarations", declarations);

        //Loop trough the declarations to get the subtotal
        double subTotal = 0;

        for (Declaration d : declarations) {
            subTotal += (d.getPrice() - d.getCompensated());
        }

        //Format for the decimal values
        DecimalFormat formatter = new DecimalFormat("€ #.00");

        //Round up the vat amount
        double vatAmount = Math.round((subTotal * company.getVat().getPercentageAmount()) * 100.0) / 100.0;

        //Add the payment info to the domain
        model.put("SubTotal", formatter.format(subTotal));
        model.put("VatAmount", formatter.format(vatAmount));
        model.put("Total", formatter.format((subTotal + vatAmount)));


        //Replace the temp fill in fields with the data
        String paymentCondition = invoice.getPaymentCondition().getTemplate();
        paymentCondition = paymentCondition.replace("%Amount%", formatter.format((subTotal + vatAmount)));
        paymentCondition = paymentCondition.replace("%period_in_days%", Integer.toString(invoice.getPaymentCondition().getPeriodInDays()));
        paymentCondition = paymentCondition.replace("%BankAccount%", company.getIban());
        paymentCondition = paymentCondition.replace("%Company_Name%", company.getCompanyName());
        paymentCondition = paymentCondition.replace("%Invoice_Ref%", Integer.toString(invoice.getId()));

        //Add the filled in condition to the domain
        model.put("PaymentCondition", paymentCondition);
        return model;
    }

}
