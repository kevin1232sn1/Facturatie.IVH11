package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.Person;
import avans.ivh11a1.facturatie.repository.CustomerRepository;
import avans.ivh11a1.facturatie.repository.UserRepository;
import avans.ivh11a1.facturatie.service.PersonFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kevin on 11-3-2017.
 */
@Service("PersonFactory")
@Repository
@Transactional(rollbackFor = StateException.class)
public class PersonFactoryImpl implements PersonFactoryService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Person getPerson(String type, int id) {
        switch (type) {
            case "Customer":
                return customerRepository.findByCsn(id);
            case "User":
                return userRepository.findOne(id);
            default:
                return null;
        }
    }

    @Override
    public Person getPerson(String type, String email) {
        switch (type) {
            case "Customer":
                return customerRepository.findByEmail(email);
            case "User":
                return userRepository.findByEmail(email);
            default:
                return null;
        }
    }
}
