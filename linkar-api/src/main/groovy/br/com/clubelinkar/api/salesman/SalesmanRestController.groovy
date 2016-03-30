package br.com.clubelinkar.api.salesman

import br.com.clubelinkar.support.mail.IMailService
import br.com.clubelinkar.support.mail.Mail
import br.com.clubelinkar.support.mail.MailTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

/**
 * @author Lennon Jesus
 */
@RestController
public class SalesmanRestController {

    @Autowired
    private SalesmanRepository salesmanRepository

    @Autowired
    private ISalesmanValidator salesmanValidator

    @Autowired
    private IMailService mailService

    @RequestMapping(value = "/salesman", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Salesman create(@RequestBody @Valid Salesman salesman) {

        salesmanValidator.validate(salesman, null)

        Salesman createdSalesman = salesmanRepository.save(salesman)

        Mail email = new Mail()
                .from("noreply@clubelinkar.com.br") // FIXME
                .to(salesman.getEmail())
                .subject("Você se cadastrou na Linkar!") // FIXME
                .template(MailTemplate.USER_REGISTRATION)
                .addParameter("name", salesman.getName()) // FIXME

        mailService.send(email)

        return createdSalesman
    }

    @RequestMapping(value = "/salesman", method = RequestMethod.GET)
    public List<Salesman> findAll() {
        return salesmanRepository.findAll()
    }

    @RequestMapping(value = "/salesman/{id}")
    public Salesman getById(@PathVariable("id") String id) {
        return salesmanRepository.findOne(id)
    }
}
