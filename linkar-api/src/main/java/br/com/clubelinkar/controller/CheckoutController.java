package br.com.clubelinkar.controller;

import br.com.clubelinkar.api.product.Product;
import br.com.clubelinkar.api.purchase.Purchase;
import br.com.clubelinkar.api.store.Store;
import br.com.clubelinkar.api.product.ProductRepository;
import br.com.clubelinkar.api.purchase.PurchaseRepository;
import br.com.clubelinkar.api.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

/**
 * @author Lennon Jesus
 */
@Controller
public class CheckoutController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @RequestMapping(value = "/checkout/{storeId}/{productId}", method = RequestMethod.GET)
    public String create(Model model, @PathVariable("storeId") String storeId,
                             @PathVariable("productId") String productId) {


        Optional<Product> product = Optional.ofNullable(productRepository.findOne(productId));

        Optional<Store> store = Optional.ofNullable(storeRepository.findOne(storeId));

        if (product.isPresent() && store.isPresent()) {
            Purchase purchase = new Purchase();

            purchase.setProductId(productId);

        }

        return "purchase/form";
    }

}