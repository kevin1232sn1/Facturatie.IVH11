package avans.ivh11a1.facturatie.Builders;

import avans.ivh11a1.facturatie.domain.insurances.Policy;

/**
 * Created by kevin on 3-4-2017.
 */
public class PolicyBuilder {
    public static Policy.PolicyBuilder builder() {
        return Policy.builder()
                .id(1)
                .active(true)
                .contribution(500)
                .contributionUsed(0)
                .customer(CustomerBuilder.builder().build())
                .dateStart("01-01-2017")
                .dateEnd("31-12-2017")
                .insurance(InsuranceBuilder.builder().build());
    }
}
