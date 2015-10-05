package br.com.clubelinkar.repository;

import br.com.clubelinkar.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lennon Jesus
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
