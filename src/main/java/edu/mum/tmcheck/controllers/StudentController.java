package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.domain.entities.User;
import edu.mum.tmcheck.domain.reports.BlockAttendanceReport;
import edu.mum.tmcheck.serviceimp.BlockEndEachStudentMeditationData;
import edu.mum.tmcheck.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Comparator;
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
    AttendanceService attendanceService;

    @RequestMapping(value = "/view/attendance_information", method = RequestMethod.GET)
    public String viewDetail(Model model, @PathVariable(required = false) Optional<Long> blockId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);
        model.addAttribute("name", currentPrincipalName);
        Student student = studentService.findByUsername(currentPrincipalName);
        model.addAttribute("student", student);

        //get blocks
        List<Block> blocks;
        if (student != null) {
            blocks = blockService.findAllByStudentId(student.getStudentRegId());
        } else {
            blocks = blockService.findAll();
        }
        Collections.sort(blocks, new Comparator<Block>() {
            @Override
            public int compare(Block o1, Block o2) {
                return o1.getStartDate().isAfter(o2.getStartDate()) ? 1 : o1.getStartDate().isBefore(o2.getStartDate()) ? -1 : 0;
            }
        });
        model.addAttribute("blocks", blocks);


        //get current block
        long defaultBlockId = blocks.get(0) != null ? blocks.get(0).getId() : 0;
        Block currentBlock = blockService.findById(blockId.orElse(defaultBlockId));
        model.addAttribute("currentBlock", currentBlock);

        model.addAttribute("blockId", blockId);
        List<BlockAttendanceReport> reports = blockAttendanceReportService.findByBlockAndStudent(currentBlock.getId(), student.getId());
        System.out.println("reports=" + reports);
        System.out.println("reports.size=" + reports.size());
        if (reports != null && reports.size() > 0) {
            model.addAttribute("report", reports.get(0));
        } else {
            reports = blockAttendanceReportService.findByBlock(currentBlock.getStartDate(), currentBlock.getEndDate());
            if (reports != null && reports.size() > 0) {
                model.addAttribute("report", reports.get(0));
            } else {
                model.addAttribute("report", new BlockAttendanceReport());
                System.out.println("There is no report information");
            }
        }
        return "student";

    }
    @ResponseBody
    @RequestMapping(value = "/view/block_information", method = RequestMethod.POST)
    public String viewDetail(Model model, @PathVariable(required = false) Optional<Long> blockId,@PathVariable(required = false) Optional<String> studentId) {
        System.out.println("Prepare data for requesting from client select options");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);
        model.addAttribute("name", currentPrincipalName);
        Student student = studentService.findByUsername(currentPrincipalName);
        model.addAttribute("student", student);
        //Long userid = userService.findUserByUserName(authentication.getName()).getId();
        BlockEndEachStudentMeditationData data = attendanceService.computeBlockEC(studentId.get(), blockId.get());
        return String.valueOf(data);
    }
}
