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

    private final InsuranceCompanyRepository insuranceCompanyRepository;
    private InsuranceCompany company;

    @Autowired
    public InsuranceCompanyServiceImpl(InsuranceCompanyRepository insuranceCompanyRepository) {
        this.insuranceCompanyRepository = insuranceCompanyRepository;
    }

    @Override
    public InsuranceCompany getCompany() {
        company = insuranceCompanyRepository.findOne(1);
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
}
