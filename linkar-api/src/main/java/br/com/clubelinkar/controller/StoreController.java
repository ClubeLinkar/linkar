package br.com.clubelinkar.controller;

import br.com.clubelinkar.api.store.Store;
import br.com.clubelinkar.api.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lennon Jesus
 */
@Controller
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @RequestMapping(value = "/store/new", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("store", new Store());

        return "store/form";
    }

    @RequestMapping(value = "/store/list", method = RequestMethod.GET)
    public String list(Model model) {
        return "store/list";
    }

}
