package br.com.clubelinkar.api.order

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author Lennon Jesus
 */
public interface OrderRepository extends MongoRepository<Order, String> {
    Order findByUserIdAndCompanyIdAndProductId(String userId, String companyId, String productId)
}
