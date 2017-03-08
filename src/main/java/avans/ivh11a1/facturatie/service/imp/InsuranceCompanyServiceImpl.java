package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.administration.InsuranceCompany;
import avans.ivh11a1.facturatie.repository.InsuranceCompanyRepository;
import avans.ivh11a1.facturatie.service.InsuranceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kevin on 7-3-2017.
 */
@Service("InsuranceCompanyService")
@Repository
@Transactional(rollbackFor = StateException.class)
public class InsuranceCompanyServiceImpl implements InsuranceCompanyService {

    @Autowired
    private InsuranceCompanyRepository insuranceCompanyRepository;

    @Override
    public Iterable<InsuranceCompany> findAll() {
        return insuranceCompanyRepository.findAll();
    }

    @Override
    public InsuranceCompany findById(int id) {
        InsuranceCompany company = insuranceCompanyRepository.findOne(id);
        return company;
    }

    @Override
    public boolean save(InsuranceCompany insuranceCompany) {
        insuranceCompanyRepository.save(insuranceCompany);
        return true;
    }

    @Override
    public boolean delete(InsuranceCompany insuranceCompany) {
        insuranceCompanyRepository.delete(insuranceCompany);
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        insuranceCompanyRepository.delete(id);
        return true;
    }
}
