package br.com.clubelinkar.api.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lennon Jesus
 */
@RestController("/store")
public class StoreRestController {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreValidator storeValidator;

    @RequestMapping(value = "/store", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Store create(@RequestBody @Valid Store store) {

        storeValidator.validate(store, null);

        return storeRepository.save(store);
    }

    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @RequestMapping(value = "/store/{id}")
    public Store getById(@PathVariable("id") String id) {
        return storeRepository.findOne(id);
    }


}
