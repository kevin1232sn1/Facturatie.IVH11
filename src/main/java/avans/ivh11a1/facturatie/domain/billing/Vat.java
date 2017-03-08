package avans.ivh11a1.facturatie.domain.billing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
/**
 * This is a vat object
 *
 * @author Bob van der Valk
 */
@Entity
@Table(name ="vat")
@Getter
@Setter
@NoArgsConstructor
public class Vat {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "percentage")
    private double percentage;

    public Vat(int id, double percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    public Vat(double percentage) {
        this.percentage = percentage;
    }

    public String getPercentage() {
        return percentage + "%";
    }

    public double getPercentageAmount() {
        return percentage / 100;
    }
}
