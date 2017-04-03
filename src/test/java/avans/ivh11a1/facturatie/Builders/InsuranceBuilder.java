package avans.ivh11a1.facturatie.Builders;

import avans.ivh11a1.facturatie.domain.insurances.Insurance;

/**
 * Created by kevin on 3-4-2017.
 */
public class InsuranceBuilder {
    public static Insurance.InsuranceBuilder builder() {
        return Insurance.builder()
                .id(1)
                .coveredTreatments(3)
                .insuranceCompany(InsuranceCompanyBuilder.builder().build())
                .monthlyFee(145)
                .name("Insurance star plus");
    }
}

