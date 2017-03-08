package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.administration.CustomUserDetails;
import avans.ivh11a1.facturatie.domain.administration.User;
import avans.ivh11a1.facturatie.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Robin on 27/10/2016.
 * Implements UserDetailsService for authentication
 *
 * @author Robin Valk
 * @version 1.0
 * @see UserDetailsService
 * @see UserDetails
 */
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    /**
     * Constructor
     *
     * @param userRepository
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Created CustomUserDetails object for authentication
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (null == user) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            return new CustomUserDetails(user);
        }
    }

}