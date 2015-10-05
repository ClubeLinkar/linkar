package br.com.clubelinkar.repository;

import br.com.clubelinkar.domain.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lennon Jesus
 */
public interface StoreRepository extends MongoRepository<Store, String> {
}
