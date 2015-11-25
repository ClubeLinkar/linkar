package br.com.clubelinkar.api.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lennon Jesus
 */
@RestController
public class CheckoutRestController {

    @Autowired
    private IOrderService purchaseService;

    @RequestMapping(value = "/api/v1/checkout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) // FIXME melhorar essa url
    public Order checkout(@RequestBody Order order) {
        return purchaseService.save(order);
    }

}
