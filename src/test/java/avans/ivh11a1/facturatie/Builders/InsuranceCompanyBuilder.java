package avans.ivh11a1.facturatie.Builders;

import avans.ivh11a1.facturatie.domain.administration.InsuranceCompany;
import avans.ivh11a1.facturatie.domain.billing.Vat;

/**
 * Created by kevin on 27-3-2017.
 */
public class InsuranceCompanyBuilder {
    public static InsuranceCompany.Builder builder() {
        return new InsuranceCompany.Builder()
                .companyName("Test Company")
                .streetname("TestStreet")
                .houseNumber("1")
                .zipcode("1111AB")
                .city("Breda")
                .phoneNumber("0681131777")
                .email("test@test.com")
                .kvkNumber(12345678)
                .vat(new Vat(21))
                .btw("NL001234567B01")
                .iban("NL05RABO1234123400");
    }
}
