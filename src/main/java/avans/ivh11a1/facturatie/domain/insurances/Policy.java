package avans.ivh11a1.facturatie.domain.insurances;

import avans.ivh11a1.facturatie.domain.customers.Customer;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "policies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policy {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    @Column(name = "contribution")
    private float contribution;
    @Column(name = "date_start")
    private String dateStart;
    @Column(name = "date_end")
    private String dateEnd;
    @Column(name = "active")
    private boolean active;
    @Column(name = "contribution_used")
    private float contributionUsed;
}
