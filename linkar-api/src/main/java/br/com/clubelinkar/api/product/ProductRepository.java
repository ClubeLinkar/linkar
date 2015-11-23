package br.com.clubelinkar.api.product;

import br.com.clubelinkar.api.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Lennon Jesus
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByStoreId(String storeId);

    Product findByIdAndStoreId(String id, String storeId);

}
