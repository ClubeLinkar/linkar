package br.com.clubelinkar.repository

import br.com.clubelinkar.LinkarApplication
import br.com.clubelinkar.api.purchase.PurchaseRepository
import br.com.clubelinkar.api.user.Customer
import br.com.clubelinkar.api.purchase.Purchase
import br.com.clubelinkar.api.user.CustomerRepository
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author Lennon Jesus
 */
@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = LinkarApplication)
@WebIntegrationTest
@Ignore // FIXME ignore
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

}
