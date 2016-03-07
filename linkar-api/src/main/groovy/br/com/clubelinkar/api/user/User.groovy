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
@EqualsAndHashCode
public final class User {

    @Id
    private String id

    @NotNull
    private String name

    @NotNull
    @Email
    private String email

    private String gender // FIXME teste

    @NotNull
    private String cpf //TODO Adicionar validacao de CPF (tem algo diferente do Stella jah implementado?)

    private String district

    @NotNull
    private String city

    @NotNull
    private String state

    @NotNull
    private String password // FIXME HASH!!!!!!! + Regras de forca de senha

    public String getId() {
        return id
    }

    public void setId(String id) {
        this.id = id
    }

    public String getName() {
        return name
    }

    public void setName(String name) {
        this.name = name
    }

    public String getEmail() {
        return email
    }

    public void setEmail(String email) {
        this.email = email
    }

    public String getGender() {
        return gender
    }

    public void setGender(String gender) {
        this.gender = gender
    }

    public String getCpf() {
        return cpf
    }

    public void setCpf(String cpf) {
        this.cpf = cpf
    }

    public String getDistrict() {
        return district
    }

    public void setDistrict(String district) {
        this.district = district
    }

    public String getCity() {
        return city
    }

    public void setCity(String city) {
        this.city = city
    }

    public String getState() {
        return state
    }

    public void setState(String state) {
        this.state = state
    }

    public String getPassword() {
        return password
    }

    public void setPassword(String password) {
        this.password = password
    }

}
