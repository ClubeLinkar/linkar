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

        def salesman = (Salesman) target

        Salesman existentSalesman = salesmanRepository.findByEmail(salesman.getEmail())

        if (existentSalesman != null && salesman.id != existentSalesman.id) {
            throw new RepeatedUserEmailException()
        }

        existentSalesman = salesmanRepository.findByCpf(salesman.getCpf())

        if (existentSalesman != null && salesman.id != existentSalesman.id) {
            throw new RepeatedUserCPFException()
        }

    }
}
