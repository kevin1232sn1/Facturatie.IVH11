package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.repository.CustomerRepository;
import avans.ivh11a1.facturatie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kevin on 7-3-2017.
 */

@Service("CustomerService")
@Repository
@Transactional(rollbackFor = StateException.class)
public class CustomerServiceImpl implements CustomerService {

    final
    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findByCsn(int CSN) {
        return customerRepository.findByCsn(CSN);
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Boolean save(Customer customer) {
        customerRepository.save(customer);
        return true;
    }

    @Override
    public Boolean delete(Customer customer) {
        customerRepository.delete(customer);
        return true;
    }

    @Override
    public Boolean deleteById(int id) {
        customerRepository.delete(id);
        return null;
    }

    @Override
    public Iterable<Customer> getCustomersByPage(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
