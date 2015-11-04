package br.com.clubelinkar.repository

import br.com.clubelinkar.LinkarAdminApplication
import br.com.clubelinkar.domain.Customer
import br.com.clubelinkar.domain.Purchase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import static org.junit.Assert.assertNotNull

/**
 * @author Lennon Jesus
 */
@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = LinkarAdminApplication)
@WebIntegrationTest
class CustomerRepositoryIntegrationTest {

    @Autowired
    def CustomerRepository customerRepository

    @Autowired
    def PurchaseRepository purchaseRepository

    @Before
    public void setupData() {
        customerRepository.save(new Customer(id: "customer_id_1", name: "Lennon Jesus", email: "lennon.jesus@gmail.com", password: "123456"))

        purchaseRepository.save(new Purchase(customerId: "customer_id_1", storeId: "store_id_1", productId: "product_id_1"))
    }

    @After
    public void destroyData() {
        customerRepository.deleteAll()
    }

    def "Deve retornar cliente e resgatar seus pontos Linkar"() {
        def customer = customerRepository.findByEmailAndPassword("lennon.jesus@gmail.com", "123456")

        assertNotNull customer



    }

}
