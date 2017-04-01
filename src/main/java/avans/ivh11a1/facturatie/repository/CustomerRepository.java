package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.customers.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
    Customer findByCsn(int csn);

    Customer findByEmail(String email);
}
