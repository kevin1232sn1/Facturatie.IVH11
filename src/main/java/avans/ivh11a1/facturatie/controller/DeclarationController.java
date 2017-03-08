package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.billing.Declaration;
import avans.ivh11a1.facturatie.repository.DeclarationRepository;
import avans.ivh11a1.facturatie.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Robin Valk on 11-10-16.
 *
 * Controller for the Declaration domain.
 * This controller regulates the mapping of the declaration page.
 * Declarations are imported via the api and therefore no other pages
 * other than the overview are required.
 *
 * @author Robin Valk
 * @version 1.0
 * @see Declaration
 * @see DeclarationRepository
 */
@Controller
@RequestMapping("/declaration")
public class DeclarationController {

    @Autowired
    private BillingService billingService;

    /**
     * Defines `module` variable for usage in the sidebar
     * @return String
     */
    @ModelAttribute("page")
    public String module() {
        return "declarations";
    }

    /**
     * Mapping of the overview page containing all the imported declarations.
     * @param model
     * @return declaration/index
     */
    @GetMapping(value = "")
    String index(Model model) {
        model.addAttribute("declarations", billingService.findAllDeclarations());

        return "declaration/index";
    }
}
