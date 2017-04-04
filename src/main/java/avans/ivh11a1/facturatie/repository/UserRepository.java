package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.administration.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

}
