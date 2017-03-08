package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.administration.InsuranceCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Matthijs Wilhelmus on 13-10-2016.
 *
 * This is the DAO for <code>InsuranceCompany</code>.
 * The DAO is responible for (C)RU(D) on insuranceCompany....
 * This DAO inherits its CRUD methods form class <code>CrudRepository</code>.
 *
 * @author Matthijs Wilhelmus
 * @version 1.0
 * @see org.springframework.data.repository.CrudRepository
 * @see InsuranceCompany
 */
@Transactional
public interface InsuranceCompanyRepository extends CrudRepository<InsuranceCompany, Integer> {

}
