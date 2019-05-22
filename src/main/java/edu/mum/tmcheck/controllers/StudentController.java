package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.domain.reports.BlockAttendanceReport;
import edu.mum.tmcheck.serviceimp.AuthenticationServiceImp;
import edu.mum.tmcheck.services.BlockAttendanceReportService;
import edu.mum.tmcheck.services.BlockService;
import edu.mum.tmcheck.services.StudentService;
import edu.mum.tmcheck.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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

    @GetMapping({"/", "/report"})
    public String viewAttendanceReport(Model model, Principal principal) {
        Student student = (Student) authenticationServiceImp.getAuthenticatedUserByUsername(principal.getName());
        model.addAttribute("student", student);

        List<Block> blocks = blockService.findAllByStudentId(student.getStudentRegId());

        Block currentBlock = blocks.get(0);
        BlockAttendanceReport currentBlockReport = blockAttendanceReportService.findByStudentRegIdAndBlockId(student.getStudentRegId(), currentBlock.getId());
        List<BlockAttendanceReport> reports = blockAttendanceReportService.findAllByStudentRegIdOrderByBlockStartDesc(student.getStudentRegId());

        model.addAttribute("currentBlockReport", currentBlockReport);
        model.addAttribute("historicBlockReports", reports);

        return "student";
    }

    @RequestMapping(value = "/view/attendance_information", method = RequestMethod.GET)
    public String viewDetail(Model model, @PathVariable(required = false) Optional<Long> blockId) {
        return "";
    }
}
