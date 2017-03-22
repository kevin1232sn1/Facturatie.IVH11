package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.administration.InsuranceCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface InsuranceCompanyRepository extends CrudRepository<InsuranceCompany, Integer> {

}
