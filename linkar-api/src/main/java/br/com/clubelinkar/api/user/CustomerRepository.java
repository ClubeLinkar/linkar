package br.com.clubelinkar.api.user;

import br.com.clubelinkar.api.user.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lennon Jesus
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByEmailAndPassword(String email, String password);

}
