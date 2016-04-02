package br.com.clubelinkar.api.product

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author Lennon Jesus
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCompanyId(String companyId);

    Product findByIdAndCompanyId(String id, String companyId);

}
