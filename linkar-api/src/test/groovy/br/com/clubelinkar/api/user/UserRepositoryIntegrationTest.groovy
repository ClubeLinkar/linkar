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
    def UserRepository userRepository

    @Autowired
    def OrderRepository purchaseRepository

    @Before
    public void setupData() {
        userRepository.save(new User(id: "user_id_1", name: "Lennon Jesus", email: "lennon.jesus@gmail.com", password: "123456"))

        purchaseRepository.save(new Order(userId: "user_id_1", storeId: "store_id_1", productId: "product_id_1"))
    }

    @After
    public void destroyData() {
        userRepository.deleteAll()
    }

}
