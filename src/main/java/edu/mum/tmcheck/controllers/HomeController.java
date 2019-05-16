package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.serviceimp.EntryAttendanceReportServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    EntryAttendanceReportServiceImp serviceImp;

    @GetMapping
    public String index(Model model){
        serviceImp.generateByEntry("NOV19");
        return "home";
    }
}
