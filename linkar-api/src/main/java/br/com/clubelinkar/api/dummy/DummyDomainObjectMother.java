package br.com.clubelinkar.api.dummy;

import br.com.clubelinkar.api.user.User;
import br.com.clubelinkar.api.product.Product;
import br.com.clubelinkar.api.store.Store;

import java.math.BigDecimal;

/**
 * @author Lennon Jesus
 */
public class DummyDomainObjectMother {

    public static User getUser() {
        User user = new User();

        user.setId("user_1");
        user.setEmail("lennon.jesus@gmail.com");
        user.setCpf("099.743.617-45");
        user.setName("Lennon Jesus");
        user.setPassword("123456");
        user.setState("RJ");

        return user;
    }

    public static Product getProduct() {
        Product product = new Product();

        product.setId("product_1");
        product.setName("Riser de Guidão");
        product.setPrice(new BigDecimal(99));
        product.setStoreId("store_1");

        return product;
    }

    public static Store getStore() {
        Store store = new Store();

        store.setId("store_1");
        store.setName("All Motos");
        store.setPassword("654321");

        return store;
    }



}
