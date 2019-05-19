package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.serviceimp.MeditationTypeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Student")
public class AttendanceController {
    @Autowired
    MeditationTypeServiceImp meditationTypeServiceImp;


    @GetMapping("/tmretreatandcheck/form")
    public String Gettmcheckform(Model model) {
        model.addAttribute("meditationtypes", meditationTypeServiceImp.findAll());
        return "tmretreatandcheckformpage";
    }

    @GetMapping("/tmretreatattendanceform")
    public String Gettmattendanceretrivalform(Model model){
        return "tmretreataretrivalform";
    }

}
