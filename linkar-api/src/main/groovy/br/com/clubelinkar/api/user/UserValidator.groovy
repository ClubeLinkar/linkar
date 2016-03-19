package br.com.clubelinkar.api.user

import br.com.clubelinkar.exception.InvalidPasswordException
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

    @Autowired
    private IPasswordPolicy passwordPolicy

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz)
    }

    @Override
    public void validate(def target, Errors errors) {

        if(!passwordPolicy.matches(target.password)) {
            throw new InvalidPasswordException()
        }

        if (null != userRepository.findByEmail(target.email)) {
            throw new RepeatedUserEmailException()
        }

        if (null != userRepository.findByCpf(target.cpf)) {
            throw new RepeatedUserCPFException()
        }
    }
}
