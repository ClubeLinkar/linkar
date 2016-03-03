package br.com.clubelinkar.api.product;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Lennon Jesus
 */
@Document
public final class Product {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String description;

//    @NotNull
    private String brand;

    @NotNull
    @Min(1)
    private BigDecimal price;

    @NotNull
    private String storeId;

    private List<String> photos = new ArrayList<>();

    private SortedSet<String> categories = new TreeSet<>();

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public SortedSet<String> getCategories() {
        return categories;
    }

    public void setCategories(SortedSet<String> categories) {
        this.categories = categories;
    }

    public void addCategory(String c) {
        categories.add(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(name, product.name)
                .append(brand, product.brand)
                .append(storeId, product.storeId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(brand)
                .append(storeId)
                .toHashCode();
    }
}
