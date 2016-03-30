package br.com.clubelinkar.api.salesman

import br.com.clubelinkar.exception.RepeatedUserCPFException
import br.com.clubelinkar.exception.RepeatedUserEmailException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * @author Lennon Jesus
 */
@Component
class SalesmanValidator implements ISalesmanValidator {

    @Autowired
    def SalesmanRepository salesmanRepository

    @Override
    public boolean supports(Class<?> clazz) {
        return Salesman.class.isAssignableFrom(clazz)
    }

    @Override
    public void validate(Object target, Errors errors) {

        Salesman existentSalesman = salesmanRepository.findByEmail(((Salesman) target).getEmail())

        if (existentSalesman != null) {
            throw new RepeatedUserEmailException()
        }

        existentSalesman = salesmanRepository.findByCpf(((Salesman) target).getCpf())

        if (existentSalesman != null) {
            throw new RepeatedUserCPFException()
        }

    }
}
