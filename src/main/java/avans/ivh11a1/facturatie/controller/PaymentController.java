package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.billing.PaymentCondition;
import avans.ivh11a1.facturatie.service.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kevin on 11-10-2016.
 */
@Controller
@RequestMapping("/payment-condition")
public class PaymentController {
    private final Logger logger = LoggerFactory.getLogger(DeclarationController.class);
    @Autowired
    private BillingService billingService;

    /**
     * Returns list with excising payment condition
     *
     * @param theModel Spring domain to attributes
     * @return Page name
     */
    @RequestMapping("")
    public String listpaymentConditions(Model theModel) {
        //Get paymentConditions from DAO
        Iterable<PaymentCondition> paymentConditionList = billingService.findAllPaymentConditions();

        //Add paymentConditions to domain
        theModel.addAttribute("paymentConditions", paymentConditionList);

        return "payment_condition/index";
    }

    /**
     * Delete's payment condition with specified id
     * @param model Spring domain to attributes
     * @param id Payment condition id
     * @return Page name
     */
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteDeclaration(Model model, @PathVariable int id) {
        logger.debug("paymentCondition, id = " + id);

        //Delete paymentCondition with Id
        PaymentCondition paymentCondition = billingService.findPaymentConditionById(id);
        this.billingService.deletePaymentCondition(paymentCondition);

        //Get paymentConditions from DAO
        Iterable<PaymentCondition> paymentConditionList = billingService.findAllPaymentConditions();

        //Add paymentConditions to domain
        model.addAttribute("paymentConditions", paymentConditionList);

        // Open view
        return "payment_condition/index";
    }

    /**
     * Gets page to add new payment condition
     * @param model Spring domain to attributes
     * @return Page name
     */
    @GetMapping("/add")
    public String addCondition(Model model) {
        model.addAttribute("paymentCondition", new PaymentCondition());
        return "payment_condition/add";
    }

    /**
     * Post page to create new payment condition
     * @param model Spring domain to attributes
     * @param paymentCondition Filled in payment condition with form fields
     * @return Page name
     */
    @PostMapping("/add")
    public String addCondition(Model model, @ModelAttribute PaymentCondition paymentCondition) {

        billingService.savePaymentCondition(paymentCondition);

        //Get paymentConditions from DAO
        Iterable<PaymentCondition> paymentConditionList = billingService.findAllPaymentConditions();

        //Add paymentConditions to domain
        model.addAttribute("paymentConditions", paymentConditionList);

        // Open view
        return "payment_condition/index";
    }

    /**
     * Edit payment condition of given id
     * @param model Spring domain to attributes
     * @param id Id of payment condition
     * @return Page name
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCondition(Model model, @PathVariable int id) {
        PaymentCondition paymentCondition = billingService.findPaymentConditionById(id);
        model.addAttribute("paymentCondition", paymentCondition);
        return "payment_condition/add";
    }

    /**
     * Get's the module of the controller
     * @return Module name
     */
    @ModelAttribute("page")
    public String module() {
        return "payment-conditions";
    }
}
