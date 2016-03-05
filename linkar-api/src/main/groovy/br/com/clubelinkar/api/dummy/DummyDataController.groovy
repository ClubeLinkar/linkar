package br.com.clubelinkar.api.dummy

import br.com.clubelinkar.api.category.CategoryRepository
import br.com.clubelinkar.api.order.OrderRepository
import br.com.clubelinkar.api.product.ProductRepository
import br.com.clubelinkar.api.store.StoreRepository
import br.com.clubelinkar.api.user.UserRepository
import br.com.clubelinkar.exception.RepeatedUserCPFException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static br.com.clubelinkar.api.dummy.DummyDomainObjectMother.*

/**
 * @author Lennon Jesus
 */

@RestController
public class DummyDataController {

    @Autowired
    private ProductRepository productRepository

    @Autowired
    private StoreRepository storeRepository

    @Autowired
    private OrderRepository orderRepository

    @Autowired
    private UserRepository userRepository

    @Autowired
    private CategoryRepository categoryRepository

    @RequestMapping("/dummy")
    public String prepareDatabase() {
        clearDatabase()
        loadData()

        return "Dumyy Loaded!"
    }

    @RequestMapping("/exception")
    public String throwException() {
        throw new RepeatedUserCPFException()
    }

    private void loadData() {
        userRepository.save(getUser())
        userRepository.save(getAnotherUser())

        storeRepository.save(getStore())
        storeRepository.save(getAnotherStore())

        productRepository.save(getProduct())
        productRepository.save(getAnotherProduct())

        categoryRepository.save(getCategory())
        categoryRepository.save(getAnotherCategory())

    }

    private void clearDatabase() {
        productRepository.deleteAll()
        storeRepository.deleteAll()
        orderRepository.deleteAll()
        userRepository.deleteAll()
        categoryRepository.deleteAll()
    }

}