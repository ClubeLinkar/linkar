package br.com.clubelinkar.api.store;

import br.com.clubelinkar.api.store.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lennon Jesus
 */
public interface StoreRepository extends MongoRepository<Store, String> {

    Store findByIdAndPassword(String id, String password);

}
