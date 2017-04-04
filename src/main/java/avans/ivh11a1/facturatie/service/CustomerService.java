package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.Exception.CustomerNotFoundException;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import org.springframework.data.domain.Pageable;

/**
 * Created by kevin on 7-3-2017.
 */
public interface CustomerService {
    Customer findByCsn(int CSN) throws CustomerNotFoundException;
    Iterable<Customer> findAll();
    Boolean save(Customer customer);
    Boolean delete(Customer customer);
    Boolean deleteById(int id);
    Iterable<Customer> getCustomersByPage(Pageable pageable);
}
