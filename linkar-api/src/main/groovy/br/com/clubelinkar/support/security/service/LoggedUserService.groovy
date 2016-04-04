package br.com.clubelinkar.support.security.service

import br.com.clubelinkar.api.user.UserRepository
import br.com.clubelinkar.support.security.domain.CurrentUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

/**
 * @author Lennon Jesus
 */
@Service
class LoggedUserService implements ILoggedUserService {

    @Autowired
    private UserRepository userRepository

    @Override
    def getCurrentLoggedUser() {
        CurrentUser principal = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        
        userRepository.findByEmail(principal.user.email)

    }

}
