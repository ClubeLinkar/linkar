package br.com.clubelinkar.api.company

import br.com.clubelinkar.support.event.objects.CompanyCreatedEvent
import br.com.clubelinkar.support.event.IEventBus
import br.com.clubelinkar.support.security.domain.CurrentUser
import br.com.clubelinkar.support.security.service.ILoggedUserService
import br.com.clubelinkar.support.security.service.LoggedUserService
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
    private CompanyRepository companyRepository

    @Autowired
    private ICompanyValidator companyValidator

    @Autowired
    private IEventBus eventBus
    
    @Autowired
    private ILoggedUserService loggedUserService

    @RequestMapping(value = "/company", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Company create(@RequestBody @Valid Company company) {

        companyValidator.validate(company, null)

        company.createdBy = loggedUserService.currentLoggedUser.id

        Company createdCompany = companyRepository.save(company)

        eventBus.publish(new CompanyCreatedEvent(createdCompany))

        return createdCompany
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public List<Company> findAll() {
        return companyRepository.findAll()
    }

    @RequestMapping(value = "/company/{id}")
    public Company getById(@PathVariable("id") String id) {
        return companyRepository.findOne(id)
    }


}
