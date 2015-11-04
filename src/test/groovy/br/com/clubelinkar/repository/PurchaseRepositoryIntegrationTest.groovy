package br.com.clubelinkar.repository

import br.com.clubelinkar.LinkarAdminApplication
import br.com.clubelinkar.domain.Purchase
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertNull
import static org.junit.Assert.assertTrue

/**
 * @author Lennon Jesus
 */
@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = LinkarAdminApplication)
@WebIntegrationTest
class PurchaseRepositoryIntegrationTest {

    @Autowired
    private static PurchaseRepository purchaseRepository

    @BeforeClass
    public static void setupData() {
        purchaseRepository.save(new Purchase(customerId: "customer_id_1", storeId: "store_id_1", productId: "product_id_1"))
        purchaseRepository.save(new Purchase(customerId: "customer_id_2", storeId: "store_id_2", productId: "product_id_2"))
    }

    @AfterClass
    public static void destroyData() {
        purchaseRepository.deleteAll()
    }

    @Test
    public void "Deve encontrar purchase preexistente"() {
        String customerId = "customer_id_1"
        String storeId = "store_id_1"
        String productId = "product_id_1"

        Purchase existingPurchase = purchaseRepository.findByCustomerIdAndStoreIdAndProductId(customerId, storeId, productId)

        assertNotNull existingPurchase
        assertEquals customerId, existingPurchase.customerId
        assertEquals storeId, existingPurchase.storeId
        assertEquals productId, existingPurchase.productId
    }

    @Test
    public void "Não deve encontrar purchase que não corresponda aos parâmetros informados"() {
        String customerId = "blah"
        String storeId = "store_id_1"
        String productId = "product_id_1"

        Purchase existingPurchase = purchaseRepository.findByCustomerIdAndStoreIdAndProductId(customerId, storeId, productId)

        assertNull existingPurchase
    }

}
