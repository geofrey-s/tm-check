package edu.mum.tmcheck.controllers.advices;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GeneralControllerAdvice {
    @ModelAttribute
    public void artefacts(Model model){
        model.addAttribute("appTitle", "Meditation Attendance");
        model.addAttribute("pageTitle", "Meditation Attendance");
    }
}
