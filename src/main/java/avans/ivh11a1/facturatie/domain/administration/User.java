package avans.ivh11a1.facturatie.domain.administration;

import avans.ivh11a1.facturatie.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
/**
 * User domain object
 * Roles can be comma separated.
 *
 * @author Bob van der Valk / Robin Valk
 * @version 1.0
 * @see BCrypt Password hashed in BCrypt.
 */
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

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    private String type = "User";


    public User(int id, String email, String name, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public User(String email, String name, String role) {
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


    public void setPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void setPasswordWithoutHash(String password) {
        this.password = password;
    }

    @Override
    public String getFullName() {
        return getName();
    }
}
