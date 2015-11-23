package br.com.clubelinkar.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lennon Jesus
 */
@RestController
public class UserRestController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer create(@RequestBody @Valid Customer customer) {
        return customerRepository.save(customer);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
