package br.com.clubelinkar.repository;

import br.com.clubelinkar.domain.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lennon Jesus
 */
public interface PurchaseRepository extends MongoRepository<Purchase, String> {
}
