package avans.ivh11a1.facturatie.controller;


import avans.ivh11a1.facturatie.crosscutting.annotations.Logger;
import avans.ivh11a1.facturatie.crosscutting.annotations.SecurityAnnotation;
import avans.ivh11a1.facturatie.crosscutting.annotations.Timer;
import avans.ivh11a1.facturatie.domain.administration.InsuranceCompany;
import avans.ivh11a1.facturatie.domain.administration.Role;
import avans.ivh11a1.facturatie.service.BillingService;
import avans.ivh11a1.facturatie.service.InsuranceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/insurance_company")
@SecurityAnnotation(allowedRole = { Role.ADMIN})
public class InsuranceCompanyController {

    final
    BillingService billingService;
    private final InsuranceCompanyService insuranceCompanyService;

    @Autowired
    public InsuranceCompanyController(BillingService billingService, InsuranceCompanyService insuranceCompanyService) {
        this.billingService = billingService;
        this.insuranceCompanyService = insuranceCompanyService;
    }

    @ModelAttribute("page")
    public String module() {
        return "insurance-companies";
    }

    // index

    /**
     * Mapping for index page
     * @param model
     * @return
     * @deprecated Not in use
     */
    @Logger
    @GetMapping(value = "")
    String index(Model model) {
        InsuranceCompany insuranceCompany = insuranceCompanyService.getCompany();

        model.addAttribute("insuranceCompany", insuranceCompany);
        model.addAttribute("vats", billingService.findAllVat());

        return "insurance_company/edit";
    }

    // create

    /**
     *
     * @deprecated There is no need for creating multiple InsuranceCompanies for there is only one.
     */
    @GetMapping(value = "/create")
    String create(Model model) {
        model.addAttribute("insuranceCompany", new InsuranceCompany());
        model.addAttribute("vats", billingService.findAllVat());

        return "insurance_company/edit";
    }

    /**
     *
     * @deprecated There is no need for creating multiple InsuranceCompanies for there is only one.
     */
    @Timer
    @PostMapping(value = "/create")
    String store(@Valid InsuranceCompany insuranceCompany, final BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "insurance_company/edit";
        }
        insuranceCompanyService.save(insuranceCompany);

        model.addAttribute("success", "Insurance Company successfully saved");

        return this.index(model);
    }


    // edit

    /**
     * Mapping for editing the current InsuranceCompany.
     * @param model
     * @param id
     * @return insurance_company/edit
     */
    @GetMapping(value = "/edit/{id}")
    String edit(Model model, @PathVariable int id) {
        model.addAttribute("insuranceCompany", insuranceCompanyService.getCompany());
        model.addAttribute("vats", billingService.findAllVat());
        model.addAttribute("success", "Insurance Company successfully saved");

        return "insurance_company/edit";
    }
}
