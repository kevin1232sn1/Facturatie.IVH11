package avans.ivh11a1.facturatie.domain.billing;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name ="treatments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
