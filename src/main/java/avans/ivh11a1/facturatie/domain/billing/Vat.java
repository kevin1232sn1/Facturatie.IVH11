package avans.ivh11a1.facturatie.domain.billing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @NotNull
    @DecimalMin(value = "0.1", inclusive = true)
    @DecimalMax(value = "100.0", inclusive = true)
    private double percentage;

    public Vat(int id, double percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    public Vat(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public String toString() {
        return percentage + "%";
    }

    public double getPercentageAmount() {
        return percentage / 100;
    }
}
