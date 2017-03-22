package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.administration.Role;
import avans.ivh11a1.facturatie.domain.administration.User;
import avans.ivh11a1.facturatie.service.UserAdministrationService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kevin on 21-3-2017.
 */
@Service("UserAdministrationService")
@Repository
@Transactional(rollbackFor = StateException.class)
@Scope("singleton")
public class UserAdministrationServiceImpl implements UserAdministrationService {
    private static User user;
    private final String LOGIN_PAGE = "/login.html";

    @Override
    public User getCurrentUser() {
        return user;
    }

    @Override
    public void setCurrentUser(User userHolder) {
        user = userHolder;
    }

    @Override
    public void logout() {
        user = null;
    }

    @Override
    public Role getUserRole() {
        return user.getRole();
    }

    @Override
    public boolean isLoggedIn(){
        return user != null;
    }
}
