package avans.ivh11a1.facturatie.domain.insurances;

import avans.ivh11a1.facturatie.domain.administration.InsuranceCompany;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "insurances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "insurance_company_id")
    private InsuranceCompany insuranceCompany;
    @Column(name = "name")
    private String name;
    @Column(name = "monthly_fee")
    private float monthlyFee;
    @Column(name = "covered_treatments")
    private int coveredTreatments;
}
