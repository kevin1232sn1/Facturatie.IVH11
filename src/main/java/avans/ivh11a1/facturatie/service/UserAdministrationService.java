package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.administration.Role;
import avans.ivh11a1.facturatie.domain.administration.User;

/**
 * Created by kevin on 21-3-2017.
 */
public interface UserAdministrationService {
    User getCurrentUser();

    void setCurrentUser(User user);

    void logout();

    Role getUserRole();
}
