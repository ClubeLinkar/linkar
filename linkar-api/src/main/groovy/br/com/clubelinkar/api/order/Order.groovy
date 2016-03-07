package br.com.clubelinkar.api.order

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Lennon Jesus
 */
@Document
@EqualsAndHashCode(includes = ["id", "userId", "storeId", "productId", "purchasePrice"])
final class Order {

    @Id
    String id

    String userId

    @Transient
    String userEmail

    @Transient
    String userPassword

    String storeId

    @Transient
    String storePassword

    String productId

    BigDecimal purchasePrice

    Date dateTime

}
