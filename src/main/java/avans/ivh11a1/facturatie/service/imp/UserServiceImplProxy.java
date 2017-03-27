package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.DashboardModel;
import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.administration.User;
import avans.ivh11a1.facturatie.domain.administration.Role;
import avans.ivh11a1.facturatie.repository.UserRepository;
import avans.ivh11a1.facturatie.service.UserAdministrationService;
import avans.ivh11a1.facturatie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Tinne on 27-3-2017.
 */
@Service("UserService")
@Repository
@Transactional(rollbackFor = StateException.class)
public class UserServiceImplProxy implements UserService {

    final
    UserRepository userRepository;

    final
    UserAdministrationService userAdministrationService;

    private UserServiceImpl trueServiceImpl;

    @Autowired
    public UserServiceImplProxy(UserRepository userRepository, UserAdministrationService userAdministrationService) {
        this.userRepository = userRepository;
        this.userAdministrationService = userAdministrationService;
        this.trueServiceImpl = new UserServiceImpl(userRepository, userAdministrationService);
    }

    @Override
    public Iterable<User> findAll() {
        return trueServiceImpl.findAll();
    }

    @Override
    public Boolean save(User user) {
        return trueServiceImpl.save(user);
    }

    @Override
    public Boolean delete(User user) {
        return trueServiceImpl.delete(user);
    }

    @Override
    public Boolean deleteById(int id) {
        return trueServiceImpl.deleteById(id);
    }

    @Override
    public Boolean loginUser(User user) {
        return trueServiceImpl.loginUser(user);
    }

    @Override
    public void logoutUser() {
        trueServiceImpl.logoutUser();
    }

    @Override
    public DashboardModel getDashboardData() {
        Role currentRole = userAdministrationService.getCurrentUser().getRole();

        /*if(currentRole == Role.ADMIN || Role.ADMINISTRATION) {
            //hoeveelheid klanten
            //hoeveelheid users (met rollen)
            //hoeveelheid lopende contracten
            //hoeveelheid subscribers (nieuwsbrief)
        }
        else if (currentRole == Role.FINANCE) {
            //hoeveelheid lopende contracten
            //Maandelijkse opbrengst insurances (monthly_fee)
            //Gemiddelde behandelingstijd
            //Gemiddelde behandelingprijs
        }
        else*/
            return trueServiceImpl.getDashboardData();
    }
}
