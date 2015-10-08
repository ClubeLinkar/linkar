package br.com.clubelinkar.controller.rest;

import br.com.clubelinkar.domain.Product;
import br.com.clubelinkar.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lennon Jesus
 */
@RestController("/product")
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product create(@RequestBody @Valid Product product) {
        return productRepository.save(product);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/product/{id}")
    public Product getById(@PathVariable("id") String id) {
        return productRepository.findOne(id);
    }

}
