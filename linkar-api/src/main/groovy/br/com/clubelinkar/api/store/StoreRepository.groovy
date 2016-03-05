package br.com.clubelinkar.api.store

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author Lennon Jesus
 */
public interface StoreRepository extends MongoRepository<Store, String> {

    Store findByIdAndPassword(String id, String password)

    Store findByEmail(String email)

    Store findByCnpj(String cnpj)
}
