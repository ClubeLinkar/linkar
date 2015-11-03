package br.com.clubelinkar.repository;

import br.com.clubelinkar.domain.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lennon Jesus
 */
public interface StoreRepository extends MongoRepository<Store, String> {

    Store findByIdAndPassword(String id, String password);

}
