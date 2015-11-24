package br.com.clubelinkar.api.user;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lennon Jesus
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmailAndPassword(String email, String password);

}
