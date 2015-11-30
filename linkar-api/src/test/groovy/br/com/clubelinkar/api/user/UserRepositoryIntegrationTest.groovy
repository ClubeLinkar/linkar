package br.com.clubelinkar.api.user

import br.com.clubelinkar.LinkarApplication
import br.com.clubelinkar.api.order.Order
import br.com.clubelinkar.api.order.OrderRepository
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
class UserRepositoryIntegrationTest {

    @Autowired
    def UserRepository customerRepository

    @Autowired
    def OrderRepository purchaseRepository

    @Before
    public void setupData() {
        customerRepository.save(new User(id: "customer_id_1", name: "Lennon Jesus", email: "lennon.jesus@gmail.com", password: "123456"))

        purchaseRepository.save(new Order(userId: "customer_id_1", storeId: "store_id_1", productId: "product_id_1"))
    }

    @After
    public void destroyData() {
        customerRepository.deleteAll()
    }

}
