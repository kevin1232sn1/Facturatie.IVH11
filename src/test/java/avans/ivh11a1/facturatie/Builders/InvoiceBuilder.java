package avans.ivh11a1.facturatie.Builders;

import avans.ivh11a1.facturatie.domain.billing.Invoice;

/**
 * Created by kevin on 3-4-2017.
 */
public class InvoiceBuilder {
    public static Invoice.InvoiceBuilder builder() {
        return Invoice.builder()
                .customer(CustomerBuilder.builder().build())
                .paymentCondition(PaymentConditionBuilder.builder().build())
                .id(1);
    }
}
