package avans.ivh11a1.facturatie.API.V1;

import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Pascal on 04-Apr-17.
 */
@RestController
@RequestMapping("/api/v1/")
public class CustomerAPIController {

    private CustomerService customerService;

    @Autowired
    public CustomerAPIController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @RequestMapping(value = "customer/{csn}", method = RequestMethod.GET)
    public Customer CustomerGet(Model model, @PathVariable int csn){
        Customer customer = customerService.findByCsn(csn);
        return customer;
    }

}
