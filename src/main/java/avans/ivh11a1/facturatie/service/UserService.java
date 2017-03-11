package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.administration.User;

/**
 * Created by kevin on 11-3-2017.
 */
public interface UserService {
    Iterable<User> findAll();

    Boolean save(User user);

    Boolean delete(User user);

    Boolean deleteById(int id);
}
