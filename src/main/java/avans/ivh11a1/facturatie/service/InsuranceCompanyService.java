package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.administration.InsuranceCompany;

/**
 * Created by kevin on 7-3-2017.
 */
public interface InsuranceCompanyService {

   InsuranceCompany getCompany();
   boolean save(InsuranceCompany insuranceCompany);
   boolean delete(InsuranceCompany insuranceCompany);
}
