package br.com.clubelinkar.service.impl;

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
import br.com.clubelinkar.service.IPurchaseService;
import br.com.clubelinkar.util.DateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lennon Jesus
 */
@Service
public class PurchaseService implements IPurchaseService {

    @Autowired
    private DateFactory dateFactory;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Purchase save(Purchase purchase) {

        validate(purchase);

        purchase.setDateTime(dateFactory.now());

        return purchaseRepository.save(purchase);
    }

    protected void validate(Purchase purchase) {
        // verificar se userId e userPass coincidem
        Customer customer = customerRepository.findByEmailAndPassword(purchase.getCustomerEmail(), purchase.getCustomerPassword());
        if (customer == null) throw new InvalidCustomerException();
        purchase.setCustomerId(customer.getId());

        // verificar se storeId e storePass coincidem
        Store store = storeRepository.findByIdAndPassword(purchase.getStoreId(), purchase.getStorePassword());
        if (store == null) throw new InvalidStoreException();

        // verificar se storeId e productStoreId coincidem
        Product product = productRepository.findByIdAndStoreId(purchase.getProductId(), purchase.getStoreId());
        if (product == null || !store.getId().equals(product.getStoreId())) throw new InvalidProductException();

        // verificar se nao existe um purchase by userId + storeId + productId
        Purchase existingPurchase = purchaseRepository.findByCustomerIdAndStoreIdAndProductId(customer.getId(), store.getId(), product.getId());
        if (existingPurchase != null) throw new RepeatedPurchaseException();
    }

}
