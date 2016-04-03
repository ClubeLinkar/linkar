package br.com.clubelinkar.api.company

import br.com.clubelinkar.exception.RepeatedStoreCNPJException
import br.com.clubelinkar.exception.RepeatedStoreEmailException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * @author Lennon Jesus
 */
@Component
public class CompanyValidator implements ICompanyValidator {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Company.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        def company = (Company) target

        Company existentCompany = companyRepository.findByEmail(company.getEmail());

        if (existentCompany != null && company.id != existentCompany.id) {
            throw new RepeatedStoreEmailException();
        }

        existentCompany = companyRepository.findByCnpj(company.getCnpj());

        if (existentCompany != null && company.id != existentCompany.id) {
            throw new RepeatedStoreCNPJException();
        }
    }
}
