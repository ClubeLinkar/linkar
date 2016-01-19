package br.com.clubelinkar.api.store;

import br.com.clubelinkar.api.user.User;
import br.com.clubelinkar.support.mail.Mail;
import br.com.clubelinkar.support.mail.MailService;
import br.com.clubelinkar.support.mail.MailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lennon Jesus
 */
@RestController("/store")
public class StoreRestController {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreValidator storeValidator;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/store", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Store create(@RequestBody @Valid Store store) {

        storeValidator.validate(store, null);

        Store createdStore = storeRepository.save(store);

        Mail email = new Mail()
                .from("noreply@clubelinkar.com.br") // FIXME
                .to(store.getEmail())
                .subject("Sua loja está na Linkar!") // FIXME
                .template(MailTemplate.STORE_REGISTRATION)
                .addParameter("name", store.getName()); // FIXME

        mailService.send(email);

        return createdStore;
    }

    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @RequestMapping(value = "/store/{id}")
    public Store getById(@PathVariable("id") String id) {
        return storeRepository.findOne(id);
    }


}
