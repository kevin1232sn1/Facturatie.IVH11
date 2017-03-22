package avans.ivh11a1.facturatie.repository;


import avans.ivh11a1.facturatie.domain.billing.Declaration;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface DeclarationRepository extends CrudRepository<Declaration, Integer> {
    List<Declaration> findByCustomer(Customer customer);

    @Query(
            value = "SELECT * FROM declarations WHERE customer_id = :Customer_Id AND (invoice_id IS NULL OR invoice_id = 0)",
            nativeQuery = true)
    List<Declaration> findOpenDeclarations(@Param("Customer_Id") int Id);

    List<Declaration> findByCustomerAndInvoiceNotNullOrderById(Customer customer);
}
