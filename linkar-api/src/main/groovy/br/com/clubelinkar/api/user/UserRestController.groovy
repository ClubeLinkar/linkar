package br.com.clubelinkar.api.user

import br.com.clubelinkar.support.event.IEventBus
import br.com.clubelinkar.support.event.objects.UserCreatedEvent
import br.com.clubelinkar.support.mail.IMailService
import br.com.clubelinkar.support.security.password.crypto.IPasswordEncrypter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

/**
 * @author Lennon Jesus
 */
@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository

    @Autowired
    private IUserValidator userValidator

    @Autowired
    private IMailService mailService

    @Autowired
    private IPasswordEncrypter encrypter

    @Autowired
    private IEventBus eventBus

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody @Valid User user) {

        userValidator.validate(user, null)

        user.password = encrypter.encrypt(user.password)

        User createdUser = userRepository.save(user)

        eventBus.publish(new UserCreatedEvent(createdUser))

        return createdUser
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> findAll() {
        return userRepository.findAll()
    }

    @RequestMapping(value = "/user/{id}")
    public User getById(@PathVariable("id") String id) {
        return userRepository.findOne(id)
    }
}
