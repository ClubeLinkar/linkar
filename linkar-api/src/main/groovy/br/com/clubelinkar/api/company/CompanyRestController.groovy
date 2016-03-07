package br.com.clubelinkar.api.company

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
@RestController("/company")
public class CompanyRestController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ICompanyValidator companyValidator;

    @Autowired
    private IMailService mailService;

    @RequestMapping(value = "/company", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Company create(@RequestBody @Valid Company company) {

        companyValidator.validate(company, null);

        Company createdCompany = companyRepository.save(company);

        Mail email = new Mail()
                .from("noreply@clubelinkar.com.br") // FIXME
                .to(company.getEmail())
                .subject("Sua loja está na Linkar!") // FIXME
                .template(MailTemplate.STORE_REGISTRATION)
                .addParameter("name", company.getName()); // FIXME

        mailService.send(email);

        return createdCompany;
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @RequestMapping(value = "/company/{id}")
    public Company getById(@PathVariable("id") String id) {
        return companyRepository.findOne(id);
    }


}
