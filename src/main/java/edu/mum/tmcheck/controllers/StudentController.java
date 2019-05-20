package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.domain.entities.User;
import edu.mum.tmcheck.domain.reports.BlockAttendanceReport;
import edu.mum.tmcheck.serviceimp.MeditationTypeServiceImp;
import edu.mum.tmcheck.services.BlockAttendanceReportService;
import edu.mum.tmcheck.services.BlockService;
import edu.mum.tmcheck.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    BlockService blockService;

    @Autowired
    BlockAttendanceReportService blockAttendanceReportService;

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/view/attendance_information", method = RequestMethod.GET)
    public String viewDetail(Model model, @PathVariable(value = "0", required = false) Long blockId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);
        model.addAttribute("name", currentPrincipalName);
        Student student = studentService.findByUsername(currentPrincipalName);
        model.addAttribute("student", student);

        //get blocks of student
        List<Block> blocks = blockService.findAll();
        Collections.sort(blocks, new Comparator<Block>() {
            @Override
            public int compare(Block o1, Block o2) {
                return o1.getStartDate().isBefore(o2.getStartDate()) ? 1 : o1.getStartDate().isAfter(o2.getStartDate()) ? -1 : 0;
            }
        });
        model.addAttribute("blocks", blocks);


        //get current block
        if (blockId == null) {
            blockId = 0L;
        }
        Block block = blockService.findById(blockId);
        if (block == null) {
            block = blockService.getFirst();
        }
        List<BlockAttendanceReport> reports = blockAttendanceReportService.findByBlockAndStudent(blockId, student.getId());
        if (reports != null && reports.size() > 0) {
            model.addAttribute("report", reports.get(0));
        } else {
            reports = blockAttendanceReportService.findByBlock(block.getStartDate(), block.getEndDate());
            if (reports != null && reports.size() > 0) {
                model.addAttribute("report", reports.get(0));
            } else {
                model.addAttribute("report", new BlockAttendanceReport());
                System.out.println("There is no report information");
            }
        }
        return "student";

    }
}
