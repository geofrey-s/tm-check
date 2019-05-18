package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.serviceimp.EntryAttendanceReportServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    EntryAttendanceReportServiceImp serviceImp;
//
//    @GetMapping
//    public String index(Model model) {
//        return "home";
//    }

    @GetMapping("/")
    public String index(Model model, Principal principal, HttpSession session){
        System.out.println(principal.getName());
        return "something";
    }

    @GetMapping("/test")
    public String test(Principal principal, Model model){
        System.out.println(principal.getName());
        return "something";
    }

}
