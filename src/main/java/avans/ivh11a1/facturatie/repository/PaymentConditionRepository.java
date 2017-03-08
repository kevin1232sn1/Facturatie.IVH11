package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.billing.PaymentCondition;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by kevin on 11-10-2016.
 */
@Transactional
public interface PaymentConditionRepository extends CrudRepository<PaymentCondition, Integer> {

}
