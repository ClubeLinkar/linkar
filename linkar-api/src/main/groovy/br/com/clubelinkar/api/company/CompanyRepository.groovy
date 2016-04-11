package br.com.clubelinkar.api.company;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lennon Jesus
 */
public interface CompanyRepository extends MongoRepository<Company, String> {

    Company findByIdAndPassword(String id, String password);

    Company findByEmail(String email);

    Company findByCnpj(String cnpj);
}
