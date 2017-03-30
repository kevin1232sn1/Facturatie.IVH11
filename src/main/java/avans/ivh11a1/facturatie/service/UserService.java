package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.dashboard.DashboardModel;
import avans.ivh11a1.facturatie.domain.administration.User;

/**
 * Created by kevin on 11-3-2017.
 */
public interface UserService {
    Iterable<User> findAll();

    User findOne(int id);
    Boolean save(User user);

    Boolean delete(User user);

    Boolean deleteById(int id);

    Boolean loginUser(User user);

    void logoutUser();

    DashboardModel getDashboardData();
}
