package br.com.clubelinkar.api.dummy;

import br.com.clubelinkar.api.user.UserRepository;
import br.com.clubelinkar.api.product.ProductRepository;
import br.com.clubelinkar.api.purchase.PurchaseRepository;
import br.com.clubelinkar.api.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.clubelinkar.api.dummy.DummyDomainObjectMother.getCustomer;
import static br.com.clubelinkar.api.dummy.DummyDomainObjectMother.getProduct;
import static br.com.clubelinkar.api.dummy.DummyDomainObjectMother.getStore;

/**
 * @author Lennon Jesus
 */

@RestController
public class DummyDataController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/dummy")
    public String prepareDatabase() {
        clearDatabase();
        loadData();

        return "Dumyy Loaded!";
    }

    private void loadData() {
        userRepository.save(getCustomer());

        storeRepository.save(getStore());

        productRepository.save(getProduct());

    }

    private void clearDatabase() {
        productRepository.deleteAll();
        storeRepository.deleteAll();
        purchaseRepository.deleteAll();
        userRepository.deleteAll();
    }

}