package avans.ivh11a1.facturatie.domain.billing;

import avans.ivh11a1.facturatie.domain.customers.Customer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="declarations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Declaration {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;
    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    @Temporal(TemporalType.DATE)
    @Column(name = "declarated_at")
    private Date declaredAt;
    @Column(name = "price")
    private float price;
    @Column(name = "compensated")
    private float compensated;
}
