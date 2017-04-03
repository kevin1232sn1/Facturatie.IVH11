package avans.ivh11a1.facturatie.Builders;

import avans.ivh11a1.facturatie.domain.billing.Treatment;

/**
 * Created by kevin on 3-4-2017.
 */
public class TreatmentBuilder {
    public static Treatment.TreatmentBuilder builder() {
        return Treatment.builder()
                .id(1)
                .duration(60)
                .name("Knee injury")
                .price(60);
    }
}
