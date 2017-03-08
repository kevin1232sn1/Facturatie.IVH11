package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.domain.insurances.Policy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Matthijs Wilhelmus on 13-10-2016.
 *
 * This is the DAO for <code>Policy</code>.
 * The DAO is responible for CRUD on policies....
 * This DAO inherits its CRUD methods form class <code>CrudRepository</code>.
 *
 * @author Matthijs Wilhelmus
 * @version 1.0
 * @see org.springframework.data.repository.CrudRepository
 * @see Policy
 */
@Transactional
public interface PolicyRepository extends CrudRepository<Policy, Integer> {
    /**
     * Finds a policy belonging to a specific customer
     * @param customer
     * @return policy of this customer
     */
    Policy findByCustomer(Customer customer);
}
