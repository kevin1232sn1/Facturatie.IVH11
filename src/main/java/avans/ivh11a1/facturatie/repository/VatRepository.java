package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.billing.Vat;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Pascal van Hoof on 13-10-2016.
 */

@Transactional
public interface VatRepository extends CrudRepository<Vat, Integer> {

}


