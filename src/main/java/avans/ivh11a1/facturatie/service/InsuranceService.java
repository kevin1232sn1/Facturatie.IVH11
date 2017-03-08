package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.domain.insurances.Insurance;
import avans.ivh11a1.facturatie.domain.insurances.Policy;

/**
 * Created by kevin on 7-3-2017.
 */
public interface InsuranceService {

   Iterable<Insurance> findAllInsurances();
   Insurance findInsuranceById(int id);
   boolean saveInsurance(Insurance insurance);
   boolean deleteInsurance(Insurance insurance);
   boolean deleteInsuranceById(int id);

   Iterable<Policy> findAllPolicies();
   Policy findPolicyByCustomer(Customer customer);
   Boolean savePolicy(Policy policy);
   boolean deletePolicy(Policy policy);
   boolean deletePolicyById(int id);
}
