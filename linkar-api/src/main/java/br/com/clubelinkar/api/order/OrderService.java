package br.com.clubelinkar.api.order;

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
public class OrderService implements IOrderService {

    @Autowired
    private DateFactory dateFactory;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Order save(Order order) {

        validate(order);

        order.setDateTime(dateFactory.now());

        return orderRepository.save(order);
    }

    protected void validate(Order order) {
        // verificar se userId e userPass coincidem
        User user = userRepository.findByEmailAndPassword(order.getUserEmail(), order.getUserPassword());
        if (user == null) throw new InvalidUserException();
        order.setUserId(user.getId());

        // verificar se storeId e storePass coincidem
        Store store = storeRepository.findByIdAndPassword(order.getStoreId(), order.getStorePassword());
        if (store == null) throw new InvalidStoreException();

        // verificar se storeId e productStoreId coincidem
        Product product = productRepository.findByIdAndStoreId(order.getProductId(), order.getStoreId());
        if (product == null || !store.getId().equals(product.getStoreId())) throw new InvalidProductException();

        // verificar se nao existe um order by userId + storeId + productId
        Order existingOrder = orderRepository.findByUserIdAndStoreIdAndProductId(user.getId(), store.getId(), product.getId());
        if (existingOrder != null) throw new RepeatedPurchaseException();
    }

}