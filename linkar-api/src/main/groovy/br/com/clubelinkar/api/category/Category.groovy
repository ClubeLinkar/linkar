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
class Category {

    @Id
    String id

    @NotNull
    String name

}
