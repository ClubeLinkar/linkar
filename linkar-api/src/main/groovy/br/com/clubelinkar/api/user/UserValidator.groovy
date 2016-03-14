package br.com.clubelinkar.api.user

import br.com.clubelinkar.exception.RepeatedUserCPFException
import br.com.clubelinkar.exception.RepeatedUserEmailException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * @author Lennon Jesus
 */
@Component
public class UserValidator implements IUserValidator {

    @Autowired
    private UserRepository userRepository

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz)
    }

    @Override
    public void validate(def target, Errors errors) {

        if (null != userRepository.findByEmail(target.getEmail())) {
            throw new RepeatedUserEmailException()
        }

        if (null != userRepository.findByCpf(target.getCpf())) {
            throw new RepeatedUserCPFException()
        }
    }
}
