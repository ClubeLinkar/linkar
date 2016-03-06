package br.com.clubelinkar.api.category

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

import javax.validation.Valid
import java.util.List

/**
 * @author Lennon Jesus
 */
@RestController("/category")
public class CategoryRestController {
    @RequestMapping(value = "/category", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category create(@RequestBody @Valid Category category) {
        return categoryRepository.save(category)
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<Category> findAll() {
        return categoryRepository.findAll()
    }

    @RequestMapping(value = "/category/{id}")
    public Category getById(@PathVariable("id") String id) {
        return categoryRepository.findOne(id)
    }

    @Autowired
    private CategoryRepository categoryRepository
}
