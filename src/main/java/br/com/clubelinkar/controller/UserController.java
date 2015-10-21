package br.com.clubelinkar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lennon Jesus
 */
@Controller
public class UserController {

    @RequestMapping(value = "/user/new", method = RequestMethod.GET)
    public String create() {
        return "customer/form";
    }

    @RequestMapping("/user/list")
    public String list() {
        return "customer/list";
    }

}
