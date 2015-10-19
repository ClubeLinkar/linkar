package br.com.clubelinkar.controller.rest;

import br.com.clubelinkar.domain.Store;
import br.com.clubelinkar.repository.StoreRepository;
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
@RestController("/api/v1/store")
public class StoreRestController {

    @Autowired
    private StoreRepository storeRepository;

    @RequestMapping(value = "/api/v1/store", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Store create(@RequestBody @Valid Store store) {
        return storeRepository.save(store);
    }

    @RequestMapping(value = "/api/v1/store", method = RequestMethod.GET)
    public List<Store> findAll() {
        return storeRepository.findAll();
    }


}
