package br.com.clubelinkar.api.category;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Lennon Jesus
 */
public interface CategoryRepository extends MongoRepository<Category, String> {
    public abstract List<Category> findAll();
}
