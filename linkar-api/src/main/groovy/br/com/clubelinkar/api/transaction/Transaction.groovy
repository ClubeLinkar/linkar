package br.com.clubelinkar.api.transaction

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Lennon Jesus
 */
@Document
@EqualsAndHashCode
class Transaction {

    @Id
    String id

    String companyId

    String companyName

    String productId

    String productName

    BigDecimal productPrice

    String customerId

    String customerEmail

    String customerName

    String creatorId

    String creatorName

    String creatorEmail

    Date dateCreated
}