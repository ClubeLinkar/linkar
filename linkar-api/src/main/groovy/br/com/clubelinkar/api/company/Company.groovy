package br.com.clubelinkar.api.company

import groovy.transform.EqualsAndHashCode
import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.hibernate.validator.constraints.Email
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import javax.validation.constraints.NotNull

/**
 * @author Lennon Jesus
 */
@Document
@EqualsAndHashCode
final class Company {

    @Id
    String id;

    @NotNull
    String cnpj;

    @NotNull
    String password; // FIXME HASH!!!!!!!

    @NotNull
    String name;

    @NotNull
    String description;

    @NotNull
    String address;

    @NotNull
    String phones;

    @NotNull
    @Email
    String email;

    String url;
}
