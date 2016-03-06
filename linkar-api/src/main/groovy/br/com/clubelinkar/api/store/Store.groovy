package br.com.clubelinkar.api.store

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
public final class Store {

    @Id
    private String id

    @NotNull
    private String cnpj

    @NotNull
    private String password // FIXME HASH!!!!!!!

    @NotNull
    private String name

    @NotNull
    private String description

    @NotNull
    private String address

    @NotNull
    private String phones

    @NotNull
    @Email
    private String email

    private String url

    public Store() {
    }

    public String getId() {
        return id
    }

    public void setId(String id) {
        this.id = id
    }

    public String getCnpj() {
        return cnpj
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj
    }

    public String getPassword() {
        return password
    }

    public void setPassword(String password) {
        this.password = password
    }

    public String getName() {
        return name
    }

    public void setName(String name) {
        this.name = name
    }

    public String getDescription() {
        return description
    }

    public void setDescription(String description) {
        this.description = description
    }

    public String getAddress() {
        return address
    }

    public void setAddress(String address) {
        this.address = address
    }

    public String getPhones() {
        return phones
    }

    public void setPhones(String phones) {
        this.phones = phones
    }

    public String getEmail() {
        return email
    }

    public void setEmail(String email) {
        this.email = email
    }

    public String getUrl() {
        return url
    }

    public void setUrl(String url) {
        this.url = url
    }

}
