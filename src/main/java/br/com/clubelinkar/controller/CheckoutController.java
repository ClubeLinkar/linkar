package br.com.clubelinkar.controller;

import br.com.clubelinkar.domain.Product;
import br.com.clubelinkar.domain.Purchase;
import br.com.clubelinkar.domain.Store;
import br.com.clubelinkar.repository.ProductRepository;
import br.com.clubelinkar.repository.PurchaseRepository;
import br.com.clubelinkar.repository.StoreRepository;
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
    public String checkout(Model model, @PathVariable("storeId") String storeId,
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
