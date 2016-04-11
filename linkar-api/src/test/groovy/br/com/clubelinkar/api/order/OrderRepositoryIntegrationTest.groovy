package br.com.clubelinkar.api.order

import br.com.clubelinkar.LinkarApplication
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import static org.junit.Assert.*

/**
 * @author Lennon Jesus
 */
@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = LinkarApplication)
@WebIntegrationTest
class OrderRepositoryIntegrationTest {

    @Autowired
    private OrderRepository purchaseRepository

    @Before
    public void setupData() {
        purchaseRepository.save(new Order(userId: "order_id_1", companyId: "store_id_1", productId: "product_id_1"))
        purchaseRepository.save(new Order(userId: "order_id_2", companyId: "store_id_2", productId: "product_id_2"))
    }

    @After
    public void destroyData() {
        purchaseRepository.deleteAll()
    }

    @Test
    public void "Deve encontrar purchase preexistente"() {
        String orderId = "order_id_1"
        String companyId = "store_id_1"
        String productId = "product_id_1"

        Order existingPurchase = purchaseRepository.findByUserIdAndCompanyIdAndProductId(orderId, companyId, productId)

        assertNotNull existingPurchase
        assertEquals orderId, existingPurchase.userId
        assertEquals companyId, existingPurchase.companyId
        assertEquals productId, existingPurchase.productId
    }

    @Test
    public void "Não deve encontrar purchase que não corresponda aos parâmetros informados"() {
        String orderId = "blah"
        String companyId = "store_id_1"
        String productId = "product_id_1"

        Order existingPurchase = purchaseRepository.findByUserIdAndCompanyIdAndProductId(orderId, companyId, productId)

        assertNull existingPurchase
    }

}
