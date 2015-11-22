package br.com.clubelinkar.dummy;

import br.com.clubelinkar.domain.Customer;
import br.com.clubelinkar.domain.Product;
import br.com.clubelinkar.domain.Store;

import java.math.BigDecimal;

/**
 * @author Lennon Jesus
 */
public class DummyDomainObjectMother {

    public static Customer getCustomer() {
        Customer customer = new Customer();

        customer.setId("customer_1");
        customer.setEmail("lennon.jesus@gmail.com");
        customer.setCpf("099.743.617-45");
        customer.setName("Lennon Jesus");
        customer.setPassword("123456");
        customer.setState("RJ");

        return customer;
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
