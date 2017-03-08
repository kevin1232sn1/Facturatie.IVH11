package avans.ivh11a1.facturatie.domain.billing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * This is the object of a treatment
 *
 * @author Bob van der Valk
 */
@Entity
@Table(name ="treatments")
@Getter
@Setter
@NoArgsConstructor
public class Treatment {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private int duration;
    @Column(name = "price")
    private float price;

    public Treatment(int id, String name, int duration, float price) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    public Treatment(String name, int duration, float price) {
        this.name = name;
        this.duration = duration;
        this.price = price;
    }


}
