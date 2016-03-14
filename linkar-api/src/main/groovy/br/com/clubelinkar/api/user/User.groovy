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
class User {

    @Id
    String id

    @NotNull
    String name

    @NotNull
    @Email
    String email

    String gender // FIXME teste

    @NotNull
    String cpf //TODO Adicionar validacao de CPF (tem algo diferente do Stella jah implementado?)

    String district

    @NotNull
    String city

    @NotNull
    String state

    @NotNull
    String password // FIXME Regras de forca de senha

}
