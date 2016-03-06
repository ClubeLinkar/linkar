package br.com.clubelinkar.api.order

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author Lennon Jesus
 */
public interface OrderRepository extends MongoRepository<Order, String> {
    Order findByUserIdAndStoreIdAndProductId(String userId, String storeId, String productId)
}
