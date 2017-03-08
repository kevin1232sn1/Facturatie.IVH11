package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This controls the customer crud and front-end
 *
 * @author Bob van der Valk
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ModelAttribute("page")
    public String module() {
        return "customers";
    }

    /**
     * Overview page
     * @return template/customer/index.html
     */
    @RequestMapping("")
    public String overview(Model model) {
        Iterable<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/index";
    }

    /**
     * Add new customers page
     * @return template/customer/add.html
     */
    @RequestMapping("/new")
    public String add(Model model) {
        return "customer/edit";
    }

    /**
     * Add new customers page
     * @return template/customer/add.html
     */
    @RequestMapping(value = "/show/{csn}")
    public String show(Model model, @PathVariable int csn) {
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(customerService.findByCsn(csn));

        model.addAttribute("customers", customers);

        return "customer/index";
    }

    /**
     * Process the saving stuff
     * @return saved message is the customer is saved
     */
    @PostMapping(value = "/store")
    String store(Model model, @ModelAttribute Customer customer) {
        customerService.save(customer);
        model.addAttribute("success", "Customer successfully saved");

        return this.overview(model);
    }

    @RequestMapping(value = "/edit/{csn}", method = RequestMethod.GET)
    public String editCustomer(Model model, @PathVariable int csn) {
        Customer customer = customerService.findByCsn(csn);
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @RequestMapping(value = "/delete/{csn}")
    public String deleteCustomer(Model model, @PathVariable int csn) {
        Customer customer = customerService.findByCsn(csn);
        customerService.delete(customer);

        model.addAttribute("success", "Customer successfully deleted!");

        return this.overview(model);
    }
}
