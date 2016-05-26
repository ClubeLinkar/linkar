package br.com.clubelinkar.api.user

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
class User implements Serializable {

    @Id
    String id

    @NotNull
    String name

    @NotNull
    @Email
    String email

    String gender // FIXME teste

    @NotNull
    String cpf

    String district

    @NotNull
    String city

    @NotNull
    String state

    @NotNull
    String password

    String role

}
