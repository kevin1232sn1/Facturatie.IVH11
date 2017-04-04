package avans.ivh11a1.facturatie.Builders;

import avans.ivh11a1.facturatie.domain.billing.Declaration;

import java.util.Date;

/**
 * Created by kevin on 3-4-2017.
 */
public class DeclarationBuilder {
    public static Declaration.DeclarationBuilder builder() {
        return Declaration.builder()
                .id(1)
                .invoice(InvoiceBuilder.builder().build())
                .compensated(100)
                .customer(CustomerBuilder.builder().build())
                .declaredAt(new Date(2017, 03, 3))
                .price(100)
                .treatment(TreatmentBuilder.builder().build());
    }
}
