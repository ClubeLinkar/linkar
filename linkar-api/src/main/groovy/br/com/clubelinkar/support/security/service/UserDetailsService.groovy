package br.com.clubelinkar.support.security.service

import br.com.clubelinkar.api.user.User
import br.com.clubelinkar.api.user.UserRepository
import br.com.clubelinkar.support.security.domain.CurrentUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

import static java.lang.String.format

/**
 * @author Lennon Jesus
 */
@Service
class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public CurrentUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)

        if (user == null) {
            throw new UsernameNotFoundException(format("User with email=%s not found", email))
        }

        return new CurrentUser(user);
    }
}
