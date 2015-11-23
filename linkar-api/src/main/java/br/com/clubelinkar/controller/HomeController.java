package br.com.clubelinkar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lennon Jesus
 */
//@Controller
@Deprecated
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin/home";
    }

}
