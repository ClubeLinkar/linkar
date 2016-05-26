package br.com.clubelinkar.api.person

import groovy.transform.EqualsAndHashCode
import org.hibernate.validator.constraints.Email
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Lennon Jesus
 */
@Document
@EqualsAndHashCode
class Person {

    @Id
    String id

    String name

    @Email
    String email

    String gender

    String cpf

    String district

    String city

    String state

}
