package avans.ivh11a1.facturatie.domain;

import avans.ivh11a1.facturatie.Builders.InsuranceCompanyBuilder;
import avans.ivh11a1.facturatie.domain.administration.InsuranceCompany;
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

     @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveCompanyWithoutKvkShouldThrowException() throws Exception {
        InsuranceCompany company = InsuranceCompanyBuilder.builder().kvkNumber(0).build();
        this.thrown.expect(ConstraintViolationException.class);
        this.thrown.expectMessage("This value is not a valid KVK number");
        this.entityManager.persistAndFlush(company);
    }

    @Test
    public void saveCompanyWithoutBtwShouldThrowException() throws Exception {
        InsuranceCompany company = InsuranceCompanyBuilder.builder().btw("").build();
        this.thrown.expect(ConstraintViolationException.class);
        this.thrown.expectMessage("This value is not a valid BTW number");
        this.entityManager.persistAndFlush(company);
    }

    @Test
    public void saveShouldPersistData(){
        InsuranceCompany company = InsuranceCompanyBuilder.builder().build();
        this.entityManager.persistAndFlush(company.getVat());
        InsuranceCompany insuranceCompany = this.entityManager.persistFlushFind(company);
        assertThat(insuranceCompany.getCompanyName()).isEqualTo(company.getCompanyName());
        assertThat(insuranceCompany.getVat().getPercentage()).isEqualTo(company.getVat().getPercentage());
    }
}
