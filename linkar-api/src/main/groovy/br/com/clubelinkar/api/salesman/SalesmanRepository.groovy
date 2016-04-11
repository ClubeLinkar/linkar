package br.com.clubelinkar.api.salesman

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author Lennon Jesus
 */
interface SalesmanRepository extends MongoRepository<Salesman, String> {

    Salesman findByEmailAndPassword(String email, String password)

    Salesman findByEmail(String email)

    Salesman findByCpf(String cpf)
}
