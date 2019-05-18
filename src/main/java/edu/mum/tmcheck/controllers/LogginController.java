package edu.mum.tmcheck.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogginController {
    @GetMapping(value = {"/", "/home"})
    public String getLoginForm() {
        return "redirct:/home";
    }

}
