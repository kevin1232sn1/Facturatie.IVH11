package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/declaration")
public class DeclarationController {

    private final BillingService billingService;

    @Autowired
    public DeclarationController(BillingService billingService) {
        this.billingService = billingService;
    }

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
