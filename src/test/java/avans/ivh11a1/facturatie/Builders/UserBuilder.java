package avans.ivh11a1.facturatie.Builders;

import avans.ivh11a1.facturatie.domain.administration.Role;
import avans.ivh11a1.facturatie.domain.administration.User;

/**
 * Created by kevin on 27-3-2017.
 */
public class UserBuilder {
    public static User.UserBuilder builder() {
        return User.builder()
                .name("Test user")
                .email("test@test.com")
                .password("test")
                .role(Role.ADMIN);
    }
}
