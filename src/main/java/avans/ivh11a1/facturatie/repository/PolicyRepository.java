package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.domain.insurances.Policy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface PolicyRepository extends CrudRepository<Policy, Integer> {
     Policy findByCustomer(Customer customer);
}
