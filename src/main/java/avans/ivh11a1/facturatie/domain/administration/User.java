package avans.ivh11a1.facturatie.domain.administration;

import avans.ivh11a1.facturatie.domain.Person;
import avans.ivh11a1.facturatie.domain.validators.EmailAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
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

    public User(int id, String email, String name, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public User(String email, String name, Role role) {
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public User(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.role = user.getRole();
    }

    public void setPasswordWithoutHash(String password) {
        this.password = password;
    }

    @Override
    public String getFullName() {
        return getName();
    }
}
