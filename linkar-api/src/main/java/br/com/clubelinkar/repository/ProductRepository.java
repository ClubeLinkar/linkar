package br.com.clubelinkar.repository;

import br.com.clubelinkar.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lennon Jesus
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByStoreId(String storeId);

    Product findByIdAndStoreId(String id, String storeId);

}
