package avans.ivh11a1.facturatie.Builders;

import avans.ivh11a1.facturatie.domain.billing.PaymentCondition;

/**
 * Created by kevin on 3-4-2017.
 */
public class PaymentConditionBuilder {
    public static PaymentCondition.PaymentConditionBuilder builder() {
        return PaymentCondition.builder()
                .name("30 days")
                .periodInDays(30)
                .template("Please pay within 30 days")
                .id(1);
    }
}
