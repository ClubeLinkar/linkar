package br.com.clubelinkar.api.user

import br.com.clubelinkar.exception.RepeatedUserCPFException
import br.com.clubelinkar.exception.RepeatedUserEmailException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

/**
 * @author Lennon Jesus
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository userRepository

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz)
    }

    @Override
    public void validate(Object target, Errors errors) {

        User existentUser = userRepository.findByEmail(((User) target).getEmail())

        if (existentUser != null) {
            throw new RepeatedUserEmailException()
        }

        existentUser = userRepository.findByCpf(((User) target).getCpf())

        if (existentUser != null) {
            throw new RepeatedUserCPFException()
        }

    }
}
