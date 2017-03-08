package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.domain.insurances.Insurance;
import avans.ivh11a1.facturatie.domain.insurances.Policy;
import avans.ivh11a1.facturatie.repository.InsuranceRepository;
import avans.ivh11a1.facturatie.repository.PolicyRepository;
import avans.ivh11a1.facturatie.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kevin on 7-3-2017.
 */
@Service("InsuranceService")
@Repository
@Transactional(rollbackFor = StateException.class)
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    PolicyRepository policyRepository;

    @Override
    public Iterable<Insurance> findAllInsurances() {
        return insuranceRepository.findAll();
    }

    @Override
    public Insurance findInsuranceById(int id) {
        return insuranceRepository.findOne(id);
    }

    @Override
    public boolean saveInsurance(Insurance insurance) {
        insuranceRepository.save(insurance);
        return true;
    }

    @Override
    public boolean deleteInsurance(Insurance insurance) {
        insuranceRepository.delete(insurance);
        return true;
    }

    @Override
    public boolean deleteInsuranceById(int id) {
        insuranceRepository.delete(id);
        return true;
    }

    @Override
    public Iterable<Policy> findAllPolicies() {
        return policyRepository.findAll();
    }

    @Override
    public Policy findPolicyByCustomer(Customer customer) {
        return policyRepository.findByCustomer(customer);
    }

    @Override
    public Boolean savePolicy(Policy policy) {
        policyRepository.save(policy);
        return true;
    }

    @Override
    public boolean deletePolicy(Policy policy) {
        policyRepository.delete(policy);
        return false;
    }

    @Override
    public boolean deletePolicyById(int id) {
        policyRepository.delete(id);
        return false;
    }
}
