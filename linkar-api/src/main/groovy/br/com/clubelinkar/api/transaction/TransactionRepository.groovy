package br.com.clubelinkar.api.transaction

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author Lennon Jesus
 */
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    public abstract List<Transaction> findAll()
}
