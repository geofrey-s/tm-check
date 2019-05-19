package edu.mum.tmcheck.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String index(Model model, Principal principal, HttpSession session) {
        System.out.println(principal.getName());
        return "something";
    }

    @GetMapping("/test")
    public String test(Principal principal, Model model) {
        System.out.println(principal.getName());
        return "something";
    }

}
