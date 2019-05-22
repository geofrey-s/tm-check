package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String index(Model model, Principal principal, HttpSession session) {
        System.out.println(principal.getName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        System.out.println("Roles: " + user.getAuthorities());
        boolean isStudent = true;
        for (GrantedAuthority a : user.getAuthorities()) {
            if (a.getAuthority().equalsIgnoreCase("ROLE_admin") || a.getAuthority().equalsIgnoreCase("ROLE_faculty")) {
                isStudent = false;
            }
        }
        if (isStudent)
            return "redirect:/student/view/attendance_information";
        else
            return "redirect:/reports/entry-attendance-report";

    }

    @RequestMapping("/login")
    public String login(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model, Principal principal){
        if(bindingResult.hasErrors()){
            return "login";
        }
        return "login";
    }

}
