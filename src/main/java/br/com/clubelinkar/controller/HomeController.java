package br.com.clubelinkar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lennon Jesus
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "home";
    }

}
