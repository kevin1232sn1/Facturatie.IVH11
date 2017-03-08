package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.insurances.Policy;
import avans.ivh11a1.facturatie.repository.CustomerRepository;
import avans.ivh11a1.facturatie.repository.InsuranceRepository;
import avans.ivh11a1.facturatie.repository.PolicyRepository;
import avans.ivh11a1.facturatie.service.CustomerService;
import avans.ivh11a1.facturatie.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Matthijs Wilhelmus on 13-10-2016.
 * This is a controller for <code>Policy</code>.
 * This controller regulates the mapping of the policy pages
 * for viewing all policies as well as creating/updating/deleting a policy using the linked DAO's
 * A policy entity has links to a customer and an insurance.
 * @author Matthijs Wilhelmus
 *
 * @version 1.0
 * @see Policy
 * @see PolicyRepository
 * @see CustomerRepository
 * @see InsuranceRepository
 *
 */
@Controller
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private CustomerService customerService;

    @ModelAttribute("page")
    public String module() {
        return "policies";
    }


    // index

    /**
     * Mapping of the index page containing all policies.
     * @param model
     * @return policy/index
     */
    @GetMapping(value = "")
    String index(Model model) {
        model.addAttribute("policies", insuranceService.findAllPolicies());

        return "policy/index";
    }


    // create

    /**
     * Mapping of the create page for creating a new policy.
     * @param model
     * @return policy/edit with blank policy creation form.
     */
    @GetMapping(value = "/create")
    String create(Model model) {
        model.addAttribute("policy", new Policy());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("insurances", insuranceService.findAllInsurances());

        return "policy/edit";
    }

    /**
     * Mapping of success message upon successfully submitting filled policy form.
     * @param model
     * @param policy
     * @return index with success message
     */
    @PostMapping(value = "/create")
    String store(Model model, @ModelAttribute Policy policy) {
        insuranceService.savePolicy(policy);

        model.addAttribute("success", "Policy successfully saved");

        return this.index(model);
    }


    // edit

    /**
     * Mapping for editing a specific policy by id.
     * @param model
     * @param id id of policy
     * @return policy/edit with filled policy form.
     */
    @GetMapping(value = "/edit/{id}")
    String edit(Model model, @PathVariable int id) {
        model.addAttribute("policy", insuranceService.findInsuranceById(id));
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("insurances", insuranceService.findAllInsurances());

        return "policy/edit";
    }


    // delete

    /**
     * Mapping for the deletion of a specific policy by id.
     * @param model
     * @param id
     * @return index with success message
     */
    @GetMapping(value = "/delete/{id}")
    String delete(Model model, @PathVariable int id){
        insuranceService.deletePolicyById(id);
        model.addAttribute("success", "Policy successfully removed");

        return this.index(model);
    }
}
