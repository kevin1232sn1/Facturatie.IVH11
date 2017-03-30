package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.dashboard.DashboardBox;
import avans.ivh11a1.facturatie.domain.dashboard.DashboardModel;
import avans.ivh11a1.facturatie.domain.administration.User;
import avans.ivh11a1.facturatie.repository.UserRepository;
import avans.ivh11a1.facturatie.service.UserAdministrationService;
import avans.ivh11a1.facturatie.service.UserService;

/**
 * Created by kevin on 11-3-2017.
 */

public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserAdministrationService userAdministrationService;

    public UserServiceImpl(UserAdministrationService userAdministrationService, UserRepository userRepository) {
        this.userAdministrationService = userAdministrationService;
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Boolean save(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean delete(User user) {
        userRepository.delete(user);
        return true;
    }

    @Override
    public Boolean deleteById(int id) {
        userRepository.delete(id);
        return null;
    }

    @Override
    public Boolean loginUser(User user) {
        User loginUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (loginUser != null){
            userAdministrationService.setCurrentUser(loginUser);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void logoutUser() {
        userAdministrationService.setCurrentUser(null);
    }

    @Override
    public DashboardModel getDashboardData() {
        DashboardModel dashboardModel = new DashboardModel();
        dashboardModel.setBox1(new DashboardBox("N/A", 0));
        dashboardModel.setBox2(new DashboardBox("N/A", 0));
        dashboardModel.setBox3(new DashboardBox("N/A", 0));
        dashboardModel.setBox4(new DashboardBox("N/A", 0));

        return dashboardModel;
    }
}
