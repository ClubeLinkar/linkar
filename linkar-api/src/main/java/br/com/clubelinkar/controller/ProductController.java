package br.com.clubelinkar.controller;

import br.com.clubelinkar.controller.rest.StoreRestController;
import br.com.clubelinkar.domain.Product;
import br.com.clubelinkar.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lennon Jesus
 */
@Controller
public class ProductController {

    @Autowired
    private StoreRepository storeRepository;

    @RequestMapping(value = "/product/new", method = RequestMethod.GET)
    public String create(Model model) {

        model.addAttribute("product", new Product());

//        model.addAttribute("stores", storeRepository.findAll());

        return "product/form";
    }

    @RequestMapping("/product/list")
    public String list() {
        return "product/list";
    }

    @RequestMapping("/product/tags")
    public String tags() {
        return "product/tags";
    }

    @RequestMapping("/product/{id}")
    public String detail(@PathVariable("id") String id, Model model) {

        model.addAttribute(storeRepository.findOne(id));

        return "product/detail";
    }

}
