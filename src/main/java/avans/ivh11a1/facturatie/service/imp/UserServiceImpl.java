package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.administration.User;
import avans.ivh11a1.facturatie.repository.UserRepository;
import avans.ivh11a1.facturatie.service.Observer;
import avans.ivh11a1.facturatie.service.Subject;
import avans.ivh11a1.facturatie.service.UserAdministrationService;
import avans.ivh11a1.facturatie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 11-3-2017.
 */
@Service("UserService")
@Repository
@Transactional(rollbackFor = StateException.class)
public class UserServiceImpl implements UserService, Subject {

    final
    UserRepository userRepository;

    final
    UserAdministrationService userAdministrationService;

    private List<Observer> observers;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserAdministrationService userAdministrationService) {
        observers = new ArrayList<>();
        this.userRepository = userRepository;
        this.userAdministrationService = userAdministrationService;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public Boolean save(User user) {
        userRepository.save(user);
        notifyObserver(user);
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
            register((Observer) userAdministrationService);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void logoutUser() {
        userAdministrationService.setCurrentUser(null);
        unRegister((Observer) userAdministrationService);
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unRegister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver(Object object) {
        for (Observer o : observers) {
            o.update(object);
        }
    }
}
