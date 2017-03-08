package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.billing.Invoice;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kevin on 11-10-2016.
 */
@Transactional
public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
    List<Invoice> findByCustomer(Customer customer);
}
