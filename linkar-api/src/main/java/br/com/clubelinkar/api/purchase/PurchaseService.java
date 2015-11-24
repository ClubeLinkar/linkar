package br.com.clubelinkar.api.purchase;

import br.com.clubelinkar.api.user.User;
import br.com.clubelinkar.api.product.Product;
import br.com.clubelinkar.api.store.Store;
import br.com.clubelinkar.exception.InvalidUserException;
import br.com.clubelinkar.exception.InvalidProductException;
import br.com.clubelinkar.exception.InvalidStoreException;
import br.com.clubelinkar.exception.RepeatedPurchaseException;
import br.com.clubelinkar.api.user.UserRepository;
import br.com.clubelinkar.api.product.ProductRepository;
import br.com.clubelinkar.api.store.StoreRepository;
import br.com.clubelinkar.support.util.DateFactory;
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
    private UserRepository userRepository;

    @Override
    public Purchase save(Purchase purchase) {

        validate(purchase);

        purchase.setDateTime(dateFactory.now());

        return purchaseRepository.save(purchase);
    }

    protected void validate(Purchase purchase) {
        // verificar se userId e userPass coincidem
        User user = userRepository.findByEmailAndPassword(purchase.getCustomerEmail(), purchase.getCustomerPassword());
        if (user == null) throw new InvalidUserException();
        purchase.setCustomerId(user.getId());

        // verificar se storeId e storePass coincidem
        Store store = storeRepository.findByIdAndPassword(purchase.getStoreId(), purchase.getStorePassword());
        if (store == null) throw new InvalidStoreException();

        // verificar se storeId e productStoreId coincidem
        Product product = productRepository.findByIdAndStoreId(purchase.getProductId(), purchase.getStoreId());
        if (product == null || !store.getId().equals(product.getStoreId())) throw new InvalidProductException();

        // verificar se nao existe um purchase by userId + storeId + productId
        Purchase existingPurchase = purchaseRepository.findByCustomerIdAndStoreIdAndProductId(user.getId(), store.getId(), product.getId());
        if (existingPurchase != null) throw new RepeatedPurchaseException();
    }

}
