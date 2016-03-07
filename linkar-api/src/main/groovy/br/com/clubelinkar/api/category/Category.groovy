package br.com.clubelinkar.api.category

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import javax.validation.constraints.NotNull

/**
 * @author Lennon Jesus
 */
@Document
@EqualsAndHashCode
public class Category {

    @Id
    private String id

    @NotNull
    private String name

    public Category() {

    }

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

}
