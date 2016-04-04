package br.com.clubelinkar.api.company

import br.com.clubelinkar.exception.InvalidPasswordException
import br.com.clubelinkar.exception.RepeatedStoreCNPJException
import br.com.clubelinkar.exception.RepeatedStoreEmailException
import br.com.clubelinkar.support.security.password.policy.IPasswordPolicy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * @author Lennon Jesus
 */
@Component
public class CompanyValidator implements ICompanyValidator {

    @Autowired
    def CompanyRepository companyRepository
    
    @Autowired
    def IPasswordPolicy passwordPolicy

    @Override
    public boolean supports(Class<?> clazz) {
        return Company.class.isAssignableFrom(clazz)
    }

    @Override
    public void validate(Object target, Errors errors) {
        def company = (Company) target

        if(!passwordPolicy.matches(company.password)) {
            throw new InvalidPasswordException()
        }

        Company existentCompany = companyRepository.findByEmail(company.getEmail())

        if (existentCompany != null && company.id != existentCompany.id) {
            throw new RepeatedStoreEmailException()
        }

        existentCompany = companyRepository.findByCnpj(company.getCnpj())

        if (existentCompany != null && company.id != existentCompany.id) {
            throw new RepeatedStoreCNPJException()
        }
    }
}
