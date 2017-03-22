package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.billing.Vat;
import avans.ivh11a1.facturatie.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/vat")
public class VatController {

    @Autowired
    private BillingService billingService;

    @ModelAttribute("page")
    public String module() {
        return "vat";
    }

    /**
     * Overview page
     * @return template/customer/index.html
     */
    @RequestMapping("")
    public String index(Model theModel) {

        //Add invoices to domain
        theModel.addAttribute("vats", getVatList());

        return "vat/index";
    }

    @RequestMapping("/new")
    public String add(Model model) {
        model.addAttribute("vat", new Vat());

        return "vat/create";
    }


    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteVat(Model model, @PathVariable int id) {
        this.billingService.deleteVatById(id);

        model.addAttribute("success", "Vat removed from the database");

        return this.index(model);
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addVat(@Valid Vat vat, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            return "vat/create";
        }

        try {
            billingService.saveVat(vat);

            model.addAttribute("success", "Vat added to the database");

            return this.index(model);
        } catch (Exception ex) {
            return "vat/create";
        }
    }

    private Iterable<Vat> getVatList(){
        return  billingService.findAllVat();
    }
}
