package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.customers.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * This controls the hibernate stuff of a customer
 *
 * @author Bob van der Valk
 */
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findByCsn(int csn);

    Customer findByEmail(String email);
}
