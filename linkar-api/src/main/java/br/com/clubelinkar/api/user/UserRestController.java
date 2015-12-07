package br.com.clubelinkar.api.user;

import br.com.clubelinkar.support.mail.Mail;
import br.com.clubelinkar.support.mail.MailService;
import br.com.clubelinkar.support.mail.MailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lennon Jesus
 */
@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody @Valid User user) {

        userValidator.validate(user, null);

        User createdUser = userRepository.save(user);

        Mail email = new Mail()
                .from("noreply@clubelinkar.com.br") // FIXME
                .to(user.getEmail())
                .subject("Test Mail") // FIXME
                .template(MailTemplate.USER_REGISTRATION)
                .addParameter("blah", "blah blah"); // FIXME

        mailService.send(email);

        return createdUser;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
