package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.crosscutting.annotations.SecurityAnnotation;
import avans.ivh11a1.facturatie.domain.Exception.CustomerNotFoundException;
import avans.ivh11a1.facturatie.domain.administration.Role;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.service.CustomerService;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
@RequestMapping("/customer")@SecurityAnnotation(allowedRole = { Role.ADMIN, Role.ADMINISTRATION, Role.FINANCE })
public class CustomerController {

    private final CustomerService customerService;
    private final Logger logger;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

        logger = Logger.getLogger("Customers");

        logger.setLevel(Level.SEVERE);
    }

    @ModelAttribute("page")
    public String module() {
        return "customers";
    }

    /**
     * Overview page
     *
     * @return template/customer/index.html
     */
    /*@RequestMapping("")
    public String overview(Model model) { //old overview
        Iterable<Customer> customers = customerService.findAll();

        model.addAttribute("customers", customers);
        model.addAttribute("currentLocale", LocaleContextHolder.getLocale());

        return "customer/index";
    }*/

    @RequestMapping("")
    public String alternativeOverview(Model model, @PageableDefault(value=3, page=0)Pageable pageable) { //pageable overview
        model.addAttribute("customers", customerService.getCustomersByPage(pageable));
        model.addAttribute("currentLocale", LocaleContextHolder.getLocale());
        model.addAttribute("pagingButtons", GeneratePagination(pageable, Iterables.size(customerService.findAll())));

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
        try {
            customers.add(customerService.findByCsn(csn));
        } catch(CustomerNotFoundException ex){
            model.addAttribute("error", ex.getMessage());
            logger.severe(ex.getMessage());
        };

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

        return "forward:/customer";
    }

    @RequestMapping(value = "/edit/{csn}", method = RequestMethod.GET)
    public String editCustomer(Model model, @PathVariable int csn) {
        Customer customer = null;
        try {
            customer = customerService.findByCsn(csn);
        } catch(CustomerNotFoundException ex){
            model.addAttribute("error", ex.getMessage());
            logger.severe(ex.getMessage());
        };
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @RequestMapping(value = "/delete/{csn}")
    public String deleteCustomer(Model model, @PathVariable int csn) throws CustomerNotFoundException {
        Customer customer;
        try {
            customer = customerService.findByCsn(csn);
            customerService.delete(customer);
            model.addAttribute("success", "Customer successfully deleted!");
        } catch(CustomerNotFoundException ex){
            model.addAttribute("error", ex.getMessage());
            logger.severe(ex.getMessage());
        }


        return "forward:/customer";
    }

    public static String GeneratePagination(Pageable pageable, int totalObjects)
    {
        int totalPages = (int)Math.floor((double) totalObjects / pageable.getPageSize()); //Testen met Math.floor en Math.ceil
        StringBuilder stringBuilder = new StringBuilder(99999); //space for 99999 characters in the builder
        for (int i = 0; i <= totalPages; i++)
        {
            int currentButtonValue = i + 1;
            if (i == pageable.getPageNumber())
            {
                stringBuilder.append("<a href=\"?page=" + i + "\" class=\"btn btn-block btn-primary\">" +
                        currentButtonValue +
                        "</a>");
            }
            else
            {
                stringBuilder.append("<a href=\"?page=" + i + "\" class=\"btn btn-block btn-info\">" +
                        currentButtonValue +
                        "</a>");
            }
        }
        return stringBuilder.toString();
    }
}
