package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.DashboardModel;
import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.administration.User;
import avans.ivh11a1.facturatie.repository.CustomerRepository;
import avans.ivh11a1.facturatie.repository.InsuranceRepository;
import avans.ivh11a1.facturatie.repository.TreatmentRepository;
import avans.ivh11a1.facturatie.repository.UserRepository;
import avans.ivh11a1.facturatie.service.UserAdministrationService;
import avans.ivh11a1.facturatie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import javafx.util.Pair;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        dashboardModel.setBox1(new Pair<String, Integer>("N/A", 0));
        dashboardModel.setBox2(new Pair<String, Integer>("N/A", 0));
        dashboardModel.setBox3(new Pair<String, Integer>("N/A", 0));
        dashboardModel.setBox4(new Pair<String, Integer>("N/A", 0));

        return dashboardModel;
    }
}
