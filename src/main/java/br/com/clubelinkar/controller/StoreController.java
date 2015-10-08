package br.com.clubelinkar.controller;

import br.com.clubelinkar.domain.Store;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lennon Jesus
 */
@Controller
public class StoreController {

    @RequestMapping(value = "/store/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("store", new Store());

        return "store/form";
    }

}
