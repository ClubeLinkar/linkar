package br.com.clubelinkar.api.store

import br.com.clubelinkar.exception.RepeatedStoreCNPJException
import br.com.clubelinkar.exception.RepeatedStoreEmailException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

/**
 * @author Lennon Jesus
 */
@Component
public class StoreValidator implements IStoreValidator {

    @Autowired
    private StoreRepository storeRepository

    @Override
    public boolean supports(Class<?> clazz) {
        return Store.class.isAssignableFrom(clazz)
    }

    @Override
    public void validate(Object target, Errors errors) {
        Store existentStore = storeRepository.findByEmail(((Store) target).getEmail())

        if (existentStore != null) {
            throw new RepeatedStoreEmailException()
        }

        existentStore = storeRepository.findByCnpj(((Store) target).getCnpj())

        if (existentStore != null) {
            throw new RepeatedStoreCNPJException()
        }
    }
}
