package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.insurances.Insurance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface InsuranceRepository extends CrudRepository<Insurance, Integer> {

}
