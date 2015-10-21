package br.com.clubelinkar.repository;

import br.com.clubelinkar.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lennon Jesus
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByEmailAndPassword(String email, String password);

}
