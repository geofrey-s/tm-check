package edu.mum.tmcheck.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {
//    @GetMapping("/")
//    public String index(Model model, Principal principal, HttpSession session) {
//        System.out.println(principal.getName());
//        return "something";
//    }
//
//    @GetMapping("/test")
//    public String test(Principal principal, Model model) {
//        System.out.println(principal.getName());
//        return "something";
//    }

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
