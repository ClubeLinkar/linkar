package br.com.clubelinkar.api.transaction

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import javax.validation.constraints.NotNull

/**
 * @author Lennon Jesus
 */
@Document
@EqualsAndHashCode
class Transaction {

    @Id
    String id

    @NotNull
    String companyId

    String companyName

    @NotNull
    String productId

    String productName

    @NotNull
    BigDecimal productUnitPrice

    @NotNull
    Integer productQuantity

    BigDecimal amount

    @NotNull
    String customerId

    @NotNull
    String customerEmail

    String customerName

    String creatorId

    String creatorName

    String creatorEmail

    Date dateCreated
}