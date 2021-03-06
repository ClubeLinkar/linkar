package br.com.clubelinkar.api.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

/**
 * @author Lennon Jesus
 */
@RestController("/product")
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository

    @RequestMapping(value = "/product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product create(@RequestBody @Valid Product product) {
        return productRepository.save(product)
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public List<Product> findAll() {
        return productRepository.findAll()
    }

    @RequestMapping(value = "/product/{id}")
    public Product getById(@PathVariable("id") String id) {
        return productRepository.findOne(id)
    }

    @RequestMapping(value = "/product/company/{id}", method = RequestMethod.GET)
    public List<Product> findByStoreId(@PathVariable("id") String id) {
        return productRepository.findByCompanyId(id)
    }

}
