package avans.ivh11a1.facturatie.domain.administration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import avans.ivh11a1.facturatie.domain.billing.Vat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @NotNull
    private String houseNumber;
    @Column(name = "zipcode")
    @Pattern(regexp = "^[1-9][0-9]{3}\\s?[a-zA-Z]{2}$", message = "De waarde van dit veld moet een geldige postcode zijn")
    private String zipcode;
    @Column(name = "city")
    private String city;
    @Column(name = "phonenumber")
    private int phoneNumber;
    @Column(name = "email")
    @Pattern(regexp = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$", message = "De waarde van dit veld moet een geldig e-mailadres zijn")
    private String email;
    @Column(name = "kvk_number")
    private int kvkNumber;
    @OneToOne
    @JoinColumn(name = "vat_id")
    private Vat vat;
    @Column(name = "btw_number")
    private String btw;
    @Column(name = "iban")
    @Pattern(regexp = "^((NO)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{3}|(NO)[0-9A-Z]{13}|(BE)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}|(BE)[0-9A-Z]{14}|(DK|FO|FI|GL|NL)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{2}|(DK|FO|FI|GL|NL)[0-9A-Z]{16}|(MK|SI)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{3}|(MK|SI)[0-9A-Z]{17}|(BA|EE|KZ|LT|LU|AT)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}|(BA|EE|KZ|LT|LU|AT)[0-9A-Z]{18}|(HR|LI|LV|CH)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{1}|(HR|LI|LV|CH)[0-9A-Z]{19}|(BG|DE|IE|ME|RS|GB)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{2}|(BG|DE|IE|ME|RS|GB)[0-9A-Z]{20}|(GI|IL)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{3}|(GI|IL)[0-9A-Z]{21}|(AD|CZ|SA|RO|SK|ES|SE|TN)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}|(AD|CZ|SA|RO|SK|ES|SE|TN)[0-9A-Z]{22}|(PT)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{1}|(PT)[0-9A-Z]{23}|(IS|TR)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{2}|(IS|TR)[0-9A-Z]{24}|(FR|GR|IT|MC|SM)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{3}|(FR|GR|IT|MC|SM)[0-9A-Z]{25}|(AL|CY|HU|LB|PL)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}|(AL|CY|HU|LB|PL)[0-9A-Z]{26}|(MU)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{2}|(MU)[0-9A-Z]{28}|(MT)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{3}|(MT)[0-9A-Z]{29})$", message = "De waarde van dit veld moet een geldige IBAN zijn")
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
