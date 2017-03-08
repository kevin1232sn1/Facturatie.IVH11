package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.insurances.Insurance;
import avans.ivh11a1.facturatie.repository.InsuranceRepository;
import avans.ivh11a1.facturatie.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Matthijs Wilhelmus on 13-10-2016.
 *
 * This is a controller for <code>Insurance</code>.
 * This controller regulates the mapping of the insurance pages
 * for viewing all insurances as well as creating/updating/deleting an insurance
 * using the linked <code>InsuranceRepository</code>.
 *
 * @author Matthijs Wilhelmus
 * @version 1.0
 * @see InsuranceRepository
 * @see  Insurance
 */
@Controller
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;

    @ModelAttribute("page")
    public String module() {
        return "insurances";
    }


    // index

    /**
     * Mapping of the index page containing all insurances.
     * @param model
     * @return insurance/index with all insurances
     */
    @GetMapping(value = "")
    String index(Model model) {
        model.addAttribute("insurances", insuranceService.findAllInsurances());

        return "insurance/index";
    }


    // create

    /**
     * Mapping of the create page for creating a new insurance.
     * @param model
     * @return insurance/edit with blank insurance creation form.
     */
    @GetMapping(value = "/create")
    String create(Model model) {
        model.addAttribute("insurance", new Insurance());

        return "insurance/edit";
    }

    /**
     * Mapping of success message upon successfully submitting filled policy form.
     * @param model
     * @param insurance
     * @return
     */
    @PostMapping(value = "/create")
    String store(Model model, @ModelAttribute Insurance insurance) {
        insuranceService.saveInsurance(insurance);

        model.addAttribute("success", "Insurance successfully saved");

        return this.index(model);
    }

    /**
     * Mapping of link to corresponding Insurance for each policy
     * @param model
     * @param id
     * @return insurance/index
     */
    @GetMapping(value = "/show/{id}")
    String show(Model model, @PathVariable int id) {
        model.addAttribute("insurances", insuranceService.findInsuranceById(id));

        return "insurance/index";
    }

    // edit

    /**
     * Mapping for editing a specific insurance by id.
     * @param model
     * @param id
     * @return insurance/edit/id with filled insurance form.
     */
    @GetMapping(value = "/edit/{id}")
    String edit(Model model, @PathVariable int id) {
        model.addAttribute("insurance", insuranceService.findInsuranceById(id));

        return "insurance/edit";
    }


    // delete

    /**
     * Mapping for the deletion of a specific insurance by id.
     * @param model
     * @param id
     * @return insurance/index with message (success or failure)
     * @throws Exception for possible Database restriction.
     */
    @GetMapping(value = "/delete/{id}")
    String delete(Model model, @PathVariable int id) {
        try {
            insuranceService.deleteInsuranceById(id);

        model.addAttribute("success", "Insurance successfully removed");
        } catch (Exception ex) {

            model.addAttribute("failure", "Insurance could not be removed - Is insurance linked to a Customer?");

        }
        return this.index(model);
    }
}
