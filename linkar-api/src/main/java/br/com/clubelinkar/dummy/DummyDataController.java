package br.com.clubelinkar.dummy;

import br.com.clubelinkar.repository.CustomerRepository;
import br.com.clubelinkar.repository.ProductRepository;
import br.com.clubelinkar.repository.PurchaseRepository;
import br.com.clubelinkar.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.clubelinkar.dummy.DummyDomainObjectMother.getCustomer;
import static br.com.clubelinkar.dummy.DummyDomainObjectMother.getProduct;
import static br.com.clubelinkar.dummy.DummyDomainObjectMother.getStore;

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
    private CustomerRepository customerRepository;

    @RequestMapping("/dummy")
    public String prepareDatabase() {
        clearDatabase();
        loadData();

        return "Dumyy Loaded!";
    }

    private void loadData() {
        customerRepository.save(getCustomer());

        storeRepository.save(getStore());

        productRepository.save(getProduct());

    }

    private void clearDatabase() {
        productRepository.deleteAll();
        storeRepository.deleteAll();
        purchaseRepository.deleteAll();
        customerRepository.deleteAll();
    }

}