package br.com.clubelinkar.controller.rest;

import br.com.clubelinkar.domain.Customer;
import br.com.clubelinkar.domain.Product;
import br.com.clubelinkar.domain.Purchase;
import br.com.clubelinkar.domain.Store;
import br.com.clubelinkar.exception.InvalidCustomerException;
import br.com.clubelinkar.exception.InvalidProductException;
import br.com.clubelinkar.exception.InvalidStoreException;
import br.com.clubelinkar.exception.RepeatedPurchaseException;
import br.com.clubelinkar.repository.CustomerRepository;
import br.com.clubelinkar.repository.ProductRepository;
import br.com.clubelinkar.repository.PurchaseRepository;
import br.com.clubelinkar.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Lennon Jesus
 */
@RestController
public class CheckoutRestController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/api/v1/checkout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) // FIXME melhorar essa url
    public Purchase checkout(@RequestBody Purchase purchase) {

        // verificar se userId e userPass coincidem
        Customer customer = customerRepository.findByEmailAndPassword(purchase.getCustomerId(), purchase.getCustomerPassword());
        if (customer == null) throw new InvalidCustomerException();

        // verificar se storeId e storePass coincidem
        Store store = storeRepository.findByIdAndPassword(purchase.getStoreId(), purchase.getStorePassword());
        if (store == null) throw new InvalidStoreException();

        // verificar se storeId e productStoreId coincidem
        Product product = productRepository.findOne(purchase.getProductId());
        if (product == null || !store.getId().equals(product.getStoreId())) throw new InvalidProductException();

        // verificar se nao existe um purchase by userId + storeId + productId
        Purchase existingPurchase = purchaseRepository.findByCustomerIdAndStoreIdAndProductId(customer.getId(), store.getId(), product.getId());
        if (existingPurchase != null) throw new RepeatedPurchaseException();

        purchase.setDateTime(LocalDateTime.now());

        return purchaseRepository.save(purchase);
    }

}
