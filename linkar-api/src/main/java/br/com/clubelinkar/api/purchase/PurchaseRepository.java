package br.com.clubelinkar.api.purchase;

import br.com.clubelinkar.api.purchase.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lennon Jesus
 */
public interface PurchaseRepository extends MongoRepository<Purchase, String> {
    Purchase findByCustomerIdAndStoreIdAndProductId(String customerId, String storeId, String productId);
}
