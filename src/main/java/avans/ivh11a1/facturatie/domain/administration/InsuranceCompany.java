package avans.ivh11a1.facturatie.domain.administration;


import avans.ivh11a1.facturatie.domain.billing.Vat;
import avans.ivh11a1.facturatie.domain.validators.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Table(name = "insurance_company")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class InsuranceCompany {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min=1, max=255)
    private String companyname;

    @Column(name = "street_name")
    @Size(min=1, max=255)
    private String streetname;

    @Column(name = "house_number")
    @HouseNumber
    private String houseNumber;

    @Column(name = "zipcode")
    @Zipcode
    private String zipcode;

    @Column(name = "city")
    @Size(min=1, max=255)
    private String city;

    @Column(name = "phonenumber")
    @PhoneNumber
    private String phoneNumber;

    @Column(name = "email")
    @EmailAddress
    private String email;

    @Column(name = "kvk_number")
    @KVKNumber
    private Integer kvkNumber;

    @OneToOne()
    @JoinColumn(name = "vat_id")
    private Vat vat;

    @Column(name = "btw_number")
    @BTWNumber
    private String btw;

    @Column(name = "iban")
    @IBAN
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
    public InsuranceCompany(int id, String companyname, String streetname, String houseNumber, String zipcode, String city, String phoneNumber, String email, int kvkNumber, Vat vat, String btw, String iban) {
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

    public InsuranceCompany(String companyname, String streetname, String houseNumber, String zipcode, String city, String phoneNumber, String email, int kvkNumber, Vat vat, String btw, String iban) {
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
