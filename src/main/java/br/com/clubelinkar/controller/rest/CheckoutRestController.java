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
import br.com.clubelinkar.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;

/**
 * @author Lennon Jesus
 */
@RestController
public class CheckoutRestController {

    @Autowired
    private IPurchaseService purchaseService;

    @RequestMapping(value = "/api/v1/checkout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) // FIXME melhorar essa url
    public Purchase checkout(@RequestBody Purchase purchase) {
        return purchaseService.save(purchase);
    }

}
