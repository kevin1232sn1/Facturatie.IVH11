package avans.ivh11a1.facturatie.domain;

import avans.ivh11a1.facturatie.domain.administration.InsuranceCompany;
import avans.ivh11a1.facturatie.domain.billing.Vat;
import avans.ivh11a1.facturatie.repository.InsuranceCompanyRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kevin on 22-3-2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class InsuranceCompanyTest {

     private InsuranceCompany company = new InsuranceCompany("Test Company", "TestStreet","1","1111AB","Breda"
            ,"0681131786","test@test.com",12345678, new Vat(21),"NL001234567B01","NL05RABO1234123400");
    @Rule
    public ExpectedException thrown = ExpectedException.none();

     @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InsuranceCompanyRepository repository;

    @Test
    public void saveCompanyWithoutKvkShouldThrowException() throws Exception {
        company.setKvkNumber(0);
        this.thrown.expect(ConstraintViolationException.class);
        this.thrown.expectMessage("This value is not a valid KVK number");
        this.entityManager.persistAndFlush(company);
    }

    @Test
    public void saveCompanyWithoutBtwShouldThrowException() throws Exception {
        company.setBtw("");
        this.thrown.expect(ConstraintViolationException.class);
        this.thrown.expectMessage("This value is not a valid BTW number");
        this.entityManager.persistAndFlush(company);
    }

    @Test
    public void saveShouldPersistData(){
        this.entityManager.persistAndFlush(company.getVat());
        InsuranceCompany insuranceCompany = this.entityManager.persistFlushFind(company);
        assertThat(insuranceCompany.getCompanyname()).isEqualTo(company.getCompanyname());
        assertThat(insuranceCompany.getVat().getPercentage()).isEqualTo(company.getVat().getPercentage());
    }
}
