package avans.ivh11a1.facturatie.domain.customers;

import avans.ivh11a1.facturatie.domain.Person;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "iban")
    private String iban;

    private String type = "Customer";

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public int getId() {
        return getCsn();
    }
}
