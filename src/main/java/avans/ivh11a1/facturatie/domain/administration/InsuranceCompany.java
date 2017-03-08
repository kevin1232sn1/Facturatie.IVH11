package avans.ivh11a1.facturatie.domain.administration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import avans.ivh11a1.facturatie.domain.billing.Vat;
import javax.persistence.*;

/**
 * This is the object of a insurance company
 *
 * @author Bob van der Valk, Matthijs Wilhelmus
 */
@Entity
@Table(name = "insurance_company")
@Getter
@Setter
@NoArgsConstructor
public class InsuranceCompany {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String companyname;
    @Column(name = "street_name")
    private String streetname;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "city")
    private String city;
    @Column(name = "phonenumber")
    private int phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "kvk_number")
    private int kvkNumber;
    @OneToOne
    @JoinColumn(name = "vat_id")
    private Vat vat;
    @Column(name = "btw_number")
    private String btw;
    @Column(name = "iban")
    private String iban;

    /**
     * Initializes an InsuranceCompany for Database
     *
     * @param id          id of Insurance Company will be used as primary key in Database
     * @param companyname name of the Insurance Company
     * @param streetname  street name of the Insurance Company
     * @param houseNumber house number of the Insurance Company
     * @param zipcode     Zip Code of the Insurance Company
     * @param city        name of the city where the Insurance Company is located
     * @param phoneNumber phone number of the Insurance Company
     * @param email       email address of the Insurance Company
     * @param kvkNumber   kvk number of the Insurance Company
     * @param vat         id of a vat percentage will be used as foreign key in Database.
     * @param btw         VAT number of the Insurance Company
     * @param iban        IBAN of the Insurance Company
     */
    public InsuranceCompany(int id, String companyname, String streetname, String houseNumber, String zipcode, String city, int phoneNumber, String email, int kvkNumber, Vat vat, String btw, String iban) {
        this.id = id;
        this.companyname = companyname;
        this.streetname = streetname;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.kvkNumber = kvkNumber;
        this.vat = vat;
        this.btw = btw;
        this.iban = iban;
    }

    public InsuranceCompany(String companyname, String streetname, String houseNumber, String zipcode, String city, int phoneNumber, String email, int kvkNumber, Vat vat, String btw, String iban) {
        this.companyname = companyname;
        this.streetname = streetname;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.kvkNumber = kvkNumber;
        this.vat = vat;
        this.btw = btw;
        this.iban = iban;
    }
}
