package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.domain.reports.BlockAttendanceReport;
import edu.mum.tmcheck.serviceimp.AuthenticationServiceImp;
import edu.mum.tmcheck.services.BlockAttendanceReportService;
import edu.mum.tmcheck.services.BlockService;
import edu.mum.tmcheck.services.StudentService;
import edu.mum.tmcheck.services.UserService;
import edu.mum.tmcheck.serviceimp.BlockEndEachStudentMeditationData;
import edu.mum.tmcheck.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    BlockService blockService;

    @Autowired
    BlockAttendanceReportService blockAttendanceReportService;

    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationServiceImp authenticationServiceImp;

    @Autowired
    AttendanceService attendanceService;

    @GetMapping({"/", "/report"})
    public String viewAttendanceReport(Model model, Principal principal) {
        Student student = (Student) authenticationServiceImp.getAuthenticatedUserByUsername(principal.getName());
        model.addAttribute("student", student);

        List<BlockAttendanceReport> reports = blockAttendanceReportService.findAllByStudentRegIdOrderByBlockStartDesc(student.getStudentRegId());
        BlockAttendanceReport currentBlockReport = reports.size() > 0 ? reports.get(0) : null;
        reports = reports.stream().skip(1).collect(Collectors.toList());

        model.addAttribute("currentBlockReport", currentBlockReport);
        model.addAttribute("historicBlockReports", reports);

        return "student";
    }
}
