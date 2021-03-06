package br.com.clubelinkar.api.company

import groovy.transform.EqualsAndHashCode
import org.hibernate.validator.constraints.Email
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import javax.validation.constraints.NotNull

/**
 * @author Lennon Jesus
 */
@Document
@EqualsAndHashCode(excludes = "password")
final class Company implements Serializable {

    @Id
    String id

    @NotNull
    String cnpj

    @NotNull
    String password

    @NotNull
    String name

    @NotNull
    String description

    @NotNull
    String address

    @NotNull
    String phones

    @NotNull
    @Email
    String email

    String url

    String responsiblePerson

    SortedSet<String> categories = new TreeSet<>()

    String createdBy

    void addCategory(String c) {
        categories.add(c)
    }
}
