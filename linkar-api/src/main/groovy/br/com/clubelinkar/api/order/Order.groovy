package br.com.clubelinkar.api.order

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document

import java.math.BigDecimal
import java.util.Date

/**
 * @author Lennon Jesus
 */
@Document
public class Order {

    @Id
    private String id

    private String userId

    @Transient
    private String userEmail

    @Transient
    private String userPassword

    private String storeId

    @Transient
    private String storePassword

    private String productId

    private BigDecimal purchasePrice

    private Date dateTime

    public Order() {
    }

    public String getId() {
        return id
    }

    public void setId(String id) {
        this.id = id
    }

    public String getUserId() {
        return userId
    }

    public void setUserId(String userId) {
        this.userId = userId
    }

    public String getUserEmail() {
        return userEmail
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail
    }

    public String getUserPassword() {
        return userPassword
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword
    }

    public String getStoreId() {
        return storeId
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId
    }

    public String getStorePassword() {
        return storePassword
    }

    public void setStorePassword(String storePassword) {
        this.storePassword = storePassword
    }

    public String getProductId() {
        return productId
    }

    public void setProductId(String productId) {
        this.productId = productId
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice
    }

    public Date getDateTime() {
        return dateTime
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false

        Order order = (Order) o

        return new EqualsBuilder()
                .append(userId, order.userId)
                .append(storeId, order.storeId)
                .append(productId, order.productId)
                .isEquals()
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(storeId)
                .append(productId)
                .toHashCode()
    }
}
