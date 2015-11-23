package br.com.clubelinkar.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lennon Jesus
 */
@RestController("/api/v1/product")
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/api/v1/product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product create(@RequestBody @Valid Product product) {
        return productRepository.save(product);
    }

    @RequestMapping(value = "/api/v1/product", method = RequestMethod.GET)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/api/v1/product/{id}")
    public Product getById(@PathVariable("id") String id) {
        return productRepository.findOne(id);
    }

    @RequestMapping(value = "/api/v1/product/store/{id}", method = RequestMethod.GET)
    public List<Product> findByStoreId(@PathVariable("id") String id) {
        return productRepository.findByStoreId(id);
    }

}
