package avans.ivh11a1.facturatie.domain.administration;

import avans.ivh11a1.facturatie.domain.Person;
import avans.ivh11a1.facturatie.domain.validators.EmailAddress;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    @EmailAddress
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Role role;

    private String type = "User";

    @Override
    public String getFullName() {
        return getName();
    }
}
