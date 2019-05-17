package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.reports.EntryAttendanceReport;
import edu.mum.tmcheck.serviceimp.EntryAttendanceReportServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    EntryAttendanceReportServiceImp serviceImp;

    @GetMapping
    public String index(Model model){
        List<EntryAttendanceReport> report = serviceImp.generateByEntry("FEB19");
        System.out.println(report.toString());

        return "home";
    }
}
