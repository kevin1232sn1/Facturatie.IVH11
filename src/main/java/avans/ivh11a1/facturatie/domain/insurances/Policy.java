package avans.ivh11a1.facturatie.domain.insurances;

import avans.ivh11a1.facturatie.domain.customers.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "policies")
@Getter
@Setter
@NoArgsConstructor
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
    private java.sql.Date dateStart;
    @Column(name = "date_end")
    private java.sql.Date dateEnd;
    @Column(name = "active")
    private boolean active;
    @Column(name = "contribution_used")
    private float contributionUsed;


    /**
     * Initializes a Policy for Database
     * @param id id of Policy will be used as primary key in Database
     * @param customer id of Customer will be used as foreign key in Database
     * @param insurance id of Insurance will be used as foreign key in Database
     * @param contribution amount of Contribution(Eigen risico)
     * @param dateStart start date of policy
     * @param dateEnd date on which the policy ends/expires
     * @param active indicates of policy is active or not
     * @param contributionUsed amount of Contribution that has been 'spent'
     */
    public Policy(int id, Customer customer, Insurance insurance, float contribution, java.sql.Date dateStart, java.sql.Date dateEnd, boolean active, float contributionUsed) {
        this.id = id;
        this.customer = customer;
        this.insurance = insurance;
        this.contribution = contribution;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
        this.contributionUsed = contributionUsed;
    }

    public Policy(float contribution, Customer customer, Insurance insurance, java.sql.Date dateStart, java.sql.Date dateEnd, boolean active, float contributionUsed) {
        this.contribution = contribution;
        this.customer = customer;
        this.insurance = insurance;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
        this.contributionUsed = contributionUsed;
    }
}
