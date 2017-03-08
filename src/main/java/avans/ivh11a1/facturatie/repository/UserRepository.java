package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.administration.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Robin on 15-10-16.
 *
 * @author Robin Valk
 * @version 1.0
 * @see User
 * @see CrudRepository
 */
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Find user by email
     * Used for login.
     *
     * @param email
     * @return
     */
    User findByEmail(String email);

}
