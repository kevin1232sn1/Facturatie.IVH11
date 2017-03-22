package avans.ivh11a1.facturatie.domain.billing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="payment_conditions")
@Getter
@Setter
@NoArgsConstructor
public class PaymentCondition {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "template")
    private String template;
    @Column(name = "period_in_days")
    private int periodInDays;

    public PaymentCondition(String name, String template, int periodInDays) {
        this.name = name;
        this.template = template;
        this.periodInDays = periodInDays;
    }
}
