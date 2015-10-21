package br.com.clubelinkar.controller.rest;

import br.com.clubelinkar.domain.Purchase;
import br.com.clubelinkar.repository.CustomerRepository;
import br.com.clubelinkar.repository.ProductRepository;
import br.com.clubelinkar.repository.PurchaseRepository;
import br.com.clubelinkar.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;

/**
 * @author Lennon Jesus
 */
public class CheckoutRestController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/api/v1/checkout", method = RequestMethod.POST) // FIXME melhorar essa url
    public Purchase checkout(Purchase purchase) {

        // verificar se userId e userPass coincidem
        // verificar se storeId e storePass coincidem
        // verificar se storeId e productStoreId coincidem
        // verificar se nao existe um purchase by userId + storeId + productId

        purchase.setDateTime(LocalDateTime.now());

        return purchaseRepository.save(purchase);
    }

}
