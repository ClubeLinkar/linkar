package br.com.clubelinkar.api.salesman

import groovy.transform.EqualsAndHashCode
import org.hibernate.validator.constraints.Email
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import javax.validation.constraints.NotNull

/**
 * @author Lennon Jesus
 */
@Document
@EqualsAndHashCode(excludes = ["password"])
final class Salesman {

    @Id
    String id

    @NotNull
    String name

    @NotNull
    @Email
    String email

    @NotNull
    String cpf

    @NotNull
    String password

    @NotNull
    String phones

}
