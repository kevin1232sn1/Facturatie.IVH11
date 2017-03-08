package avans.ivh11a1.facturatie.domain.billing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import javax.persistence.*;

import java.util.Date;

/**
 * This is a object of a declaration
 *
 * @author Bob van der Valk
 */
@Entity
@Table(name ="declarations")
@Getter
@Setter
@NoArgsConstructor
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

    public Declaration(int id, Customer customer, Treatment treatment, Invoice invoice, Date declaredAt, float price, float compensated) {
        this.id = id;
        this.customer = customer;
        this.treatment = treatment;
        this.invoice = invoice;
        this.declaredAt = declaredAt;
        this.price = price;
        this.compensated = compensated;
    }

    public Declaration(Customer customer, Treatment treatment, Invoice invoice, Date declaredAt, float price, float compensated) {
        this.customer = customer;
        this.treatment = treatment;
        this.invoice = invoice;
        this.declaredAt = declaredAt;
        this.price = price;
        this.compensated = compensated;
    }
}
