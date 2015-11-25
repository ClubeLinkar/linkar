package br.com.clubelinkar.api.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lennon Jesus
 */
@RestController
public class OrderRestController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order checkout(@RequestBody Order order) {
        return orderService.save(order);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public List<Order> findAll() {
        return orderRepository.findAll(); // FIXME REMOVER ACESSO A ESTE METODO
    }

}
