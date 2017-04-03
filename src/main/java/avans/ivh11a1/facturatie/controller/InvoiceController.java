package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.crosscutting.annotations.SecurityAnnotation;
import avans.ivh11a1.facturatie.domain.administration.Role;
import avans.ivh11a1.facturatie.domain.billing.Invoice;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.service.BillingService;
import avans.ivh11a1.facturatie.service.CustomerService;
import avans.ivh11a1.facturatie.service.InsuranceService;
import avans.ivh11a1.facturatie.service.InvoiceGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by kevin on 11-10-2016.
 */
@Controller
@RequestMapping("/invoice")
@SecurityAnnotation(allowedRole = { Role.ADMIN, Role.FINANCE })
public class InvoiceController {
    private final Logger logger = LoggerFactory.getLogger(DeclarationController.class);

    private final BillingService billingService;
    private final CustomerService customerService;
    private final InsuranceService insuranceService;
    private final InvoiceGeneration invoiceGeneration;

    @Autowired
    public InvoiceController(BillingService billingService, CustomerService customerService, InsuranceService insuranceService, InvoiceGeneration invoiceGeneration) {
        this.billingService = billingService;
        this.customerService = customerService;
        this.insuranceService = insuranceService;
        this.invoiceGeneration = invoiceGeneration;
    }


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
        model.addAllAttributes(invoiceGeneration.printInvoice(id, model.asMap()));
        //Return the view
        return "invoice/print";
    }

    /**
     * Generates a new invoice for a given customer
     * @param model
     */
    @PostMapping("/create")
    public String GenerateInvoice(Model model, @ModelAttribute Invoice invoice) {
        model.addAllAttributes(invoiceGeneration.generateInvoice(invoice, model.asMap()));
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
