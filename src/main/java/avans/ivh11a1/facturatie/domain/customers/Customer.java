package avans.ivh11a1.facturatie.domain.customers;

import avans.ivh11a1.facturatie.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Object of a customer
 *  TODO: Policy relatie terug zetten - Even verwijderd voor hibernate
 * @author Bob van der Valk
 */
@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer implements Person {
    @Id
    @Column(name = "csn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int csn;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "city")
    private String city;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "iban")
    private String iban;

    private String type = "Customer";

    public Customer(int csn, String firstName, String lastName, String streetName, String houseNumber, String zipcode,
                    String city, String dateOfBirth, String phoneNumber, String email, String iban) {
        this.csn = csn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.iban = iban;
    }

    public Customer(String firstName, String lastName, String streetName, String houseNumber, String zipcode,
                    String city, String dateOfBirth, String phoneNumber, String email, String iban) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.iban = iban;
    }

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public int getId() {
        return getCsn();
    }
}
