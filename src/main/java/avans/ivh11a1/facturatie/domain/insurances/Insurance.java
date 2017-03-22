package avans.ivh11a1.facturatie.domain.insurances;

import avans.ivh11a1.facturatie.domain.administration.InsuranceCompany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "insurances")
@Getter
@Setter
@NoArgsConstructor
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

    /**
     * Initializes an Insurance for Database
     * @param id of Insurance will be used as primary key in Database
     * @param insuranceCompany id of InsuranceCompany will be used as foreign key inn database
     * @param name of the Insurance
     * @param monthlyFee price of Insurance per month
     * @param coveredTreatments amount of treatments covered by this Insurance
     */
    public Insurance(int id, InsuranceCompany insuranceCompany, String name, float monthlyFee, int coveredTreatments) {
        this.id = id;
        this.insuranceCompany = insuranceCompany;
        this.name = name;
        this.monthlyFee = monthlyFee;
        this.coveredTreatments = coveredTreatments;
    }

    public Insurance(InsuranceCompany insuranceCompany, String name, float monthlyFee, int coveredTreatments) {
        this.insuranceCompany = insuranceCompany;
        this.name = name;
        this.monthlyFee = monthlyFee;
        this.coveredTreatments = coveredTreatments;
    }
}
