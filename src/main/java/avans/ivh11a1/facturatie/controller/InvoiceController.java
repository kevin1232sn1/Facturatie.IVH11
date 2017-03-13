package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.administration.InsuranceCompany;
import avans.ivh11a1.facturatie.domain.billing.Declaration;
import avans.ivh11a1.facturatie.domain.billing.Invoice;
import avans.ivh11a1.facturatie.domain.billing.PaymentCondition;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.domain.insurances.Policy;
import avans.ivh11a1.facturatie.repository.*;
import avans.ivh11a1.facturatie.service.BillingService;
import avans.ivh11a1.facturatie.service.CustomerService;
import avans.ivh11a1.facturatie.service.InsuranceService;
import avans.ivh11a1.facturatie.service.InvoiceService;
import avans.ivh11a1.facturatie.service.imp.InsuranceServiceImpl;
import avans.ivh11a1.facturatie.service.imp.InvoiceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by kevin on 11-10-2016.
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    private final Logger logger = LoggerFactory.getLogger(DeclarationController.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private InvoiceService invoiceService;


    /**
     * Returns page name for displaying invoices in a list
     *
     * @param theModel
     * @return Page name
     */
    @RequestMapping("")
    public String listInvoices(Model theModel) {
        //Get invoices from DAO
        Iterable<Invoice> invoiceList = billingService.findAllInvoices();

        //Add invoices to domain
        theModel.addAttribute("invoices", invoiceList);
        theModel.addAttribute("paymentConditions", billingService.findAllPaymentConditions());

        //Return page name
        return "invoice/index";
    }

    @RequestMapping(value = "/show/{csn}", method = RequestMethod.GET)
    public String showInvoiceByCsn(Model theModel, @PathVariable int csn) {
        //Get Customer by csn
        Customer customer = customerService.findByCsn(csn);

        //Get invoices from DAO
        Iterable<Invoice> invoiceList = billingService.findInvoicesByCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);

        //Add invoices to domain
        theModel.addAttribute("invoices", invoiceList);
        theModel.addAttribute("invoice", invoice);
        theModel.addAttribute("paymentConditions", billingService.findAllPaymentConditions());
        theModel.addAttribute("customer", customer);

        //Return page name
        return "invoice/index";
    }

    /**
     * Deletes invoice by given id in the path variable
     * @param model
     * @param id Invoice id
     * @return Page name
     */
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteDeclaration(Model model, @PathVariable int id) {
        logger.debug("Invoice, id = " + id);

        //Delete invoice with Id
        boolean success = billingService.deleteInvoiceById(id);

        //Add successful message to the domain
        if (success) {
            model.addAttribute("success", "Invoice removed!");
        }else {
            model.addAttribute("success", "Something went wrong deleting the invoice");
        }

        //Return list with invoices page
        return this.listInvoices(model);
    }

    /**
     * Set payment day and status of a invoice to paid
     * @param model
     * @param id Invoice id
     * @return Page name
     */
    @RequestMapping(value = "/pay/{id}", method = RequestMethod.GET)
    public String payDeclaration(Model model, @PathVariable int id) {
        //Find invoice with given id
        Invoice invoice = billingService.findInvoiceById(id);

        //Set the date payed to today and set the state to paid
        invoice.setDatePayed(new Date());
        invoice.setState(1);

        //Save invoice
        billingService.saveInvoice(invoice);

        //Add successful message to the domain
        model.addAttribute("success", "Invoice marked as paid!");

        // Open view
        return this.listInvoices(model);
    }

    /**
     * Changes the payment condition of a certain invoice
     * @param model
     * @param id Invoice id
     * @param paymentConditionId Payment condition id to change into
     * @return Page name
     */
    @RequestMapping(value = "/{id}/change-payment-condition/{paymentConditionId}", method = RequestMethod.GET)
    public String changePaymenyCondition(Model model, @PathVariable int id, @PathVariable int paymentConditionId) {
        //Find invoice with given id
        Invoice invoice = billingService.findInvoiceById(id);

        //Set the given payment condition
        invoice.setPaymentCondition(billingService.findPaymentConditionById(paymentConditionId));

        //Save invoice
        billingService.saveInvoice(invoice);

        //Add successful message to the domain
        model.addAttribute("success", "Payment Condition updated!");

        // Open view
        return this.listInvoices(model);
    }

    /**
     * Print a certain invoice based on the given id
     * @param model
     * @param id Invoice id
     * @return Page for printing the invoice
     */
    @RequestMapping(value = "/print/{id}", method = RequestMethod.GET)
    public String PrintInvoice(Model model, @PathVariable int id) {
        //Find all the needed info to print the invoice
        Invoice invoice = billingService.findInvoiceById(id);
        Customer customer = invoice.getCustomer();
        Policy policy = insuranceService.findPolicyByCustomer(customer);
        InsuranceCompany company = policy.getInsurance().getInsuranceCompany();
        Iterable<Declaration> declarations = billingService.findDeclarationByCustomer(customer);

        //Add the general info to the domain
        model.addAttribute("Invoice", invoice);
        model.addAttribute("Customer", customer);
        model.addAttribute("Company", company);
        model.addAttribute("Declarations", declarations);

        //Loop trough the declarations to get the subtotal
        double subTotal = 0;
        for (Declaration d : declarations) {
            subTotal += (d.getPrice() - d.getCompensated());
        }

        //Format for the decimal values
        DecimalFormat formatter = new DecimalFormat("€ #,##0.00");

        //Round up the vat amount
        double vatAmount = Math.round((subTotal * company.getVat().getPercentageAmount()) * 100.0) / 100.0;

        //Add the payment info to the domain
        model.addAttribute("SubTotal", formatter.format(subTotal));
        model.addAttribute("VatAmount", formatter.format(vatAmount));
        model.addAttribute("Total", formatter.format((subTotal + vatAmount)));


        //Replace the temp fill in fields with the data
        String paymentCondition = invoice.getPaymentCondition().getTemplate();
        paymentCondition = paymentCondition.replace("%Amount%", formatter.format((subTotal + vatAmount)));
        paymentCondition = paymentCondition.replace("%period_in_days%", Integer.toString(invoice.getPaymentCondition().getPeriodInDays()));
        paymentCondition = paymentCondition.replace("%BankAccount%", company.getIban());
        paymentCondition = paymentCondition.replace("%Company_Name%", company.getCompanyname());
        paymentCondition = paymentCondition.replace("%Invoice_Ref%", Integer.toString(invoice.getId()));

        //Add the filled in condition to the domain
        model.addAttribute("PaymentCondition", paymentCondition);

        invoiceService.generatePdf(id);
        //Return the view
        return "invoice/print";
    }
    /**
     * Download a certain invoice based on the given id
     * @param model
     * @param id Invoice id
     * @return Page for downloading the invoice
     */
    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public String DownloadInvoice( @PathVariable int id) {
        //Download  PDF
        invoiceService.generatePdf(id);

        //Return to view
        return "invoice/index";
    }
    /**
     * Generates a new invoice for a given customer
     * @param model
     */
    @PostMapping("/create")
    public String GenerateInvoice(Model model, @ModelAttribute Invoice invoice) {
        String url = "https://ivh5c1-api.herokuapp.com/import/" + invoice.getCustomer().getCsn();

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.getInputStream();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
                model.addAttribute("failure", "No declarations for this customer at this moment");
            }
        } else {
            model.addAttribute("failure", "No policy for this customer at this moment");
        }
        return listInvoices(model);
    }

    /**
     * Module name of page
     * @return returns the module name
     */
    @ModelAttribute("page")
    public String module() {
        return "invoices";
    }
}
