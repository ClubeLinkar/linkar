package br.com.clubelinkar.api.product

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * @author Lennon Jesus
 */
@Document
@EqualsAndHashCode
final class Product {

    @Id
    String id;

    @NotNull
    String name;

    @NotNull
    String description;

//    @NotNull
    String brand;

    @NotNull
    @Min(value = 1L)
    BigDecimal price;

    @NotNull
    String storeId;

    List<String> photos = new ArrayList<>();

    SortedSet<String> categories = new TreeSet<>();

    void addCategory(String c) {
        categories.add(c);
    }

}
