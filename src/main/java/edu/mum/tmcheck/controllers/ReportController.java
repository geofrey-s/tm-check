package edu.mum.tmcheck.controllers;


import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.serviceimp.AttendanceServiceImp;
import edu.mum.tmcheck.serviceimp.BlockEndEachStudentMeditationData;
import edu.mum.tmcheck.serviceimp.BlockServiceImp;
import edu.mum.tmcheck.serviceimp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/students/attendancereport")
public class ReportController {

    @Autowired
    BlockServiceImp blockServiceImp;

    @Autowired
    AttendanceServiceImp attendanceServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/ExtraCredit")
    public String ExtraCreditReport(Principal principal, Model model) {
        Long userid = userServiceImp.findUserByUserName(principal.getName()).getId();
        List<Block> facultyblocks = blockServiceImp.getfacultyteachingblocks(userid);
        model.addAttribute("facultyblocks", facultyblocks);
        return "BlockECReportFormPage";
    }

    @PostMapping("/ProcessExtraCreditforstudents")
    public String GetStudetsExtraCredit(@RequestParam("block") String blockid, Principal principal, Model model, RedirectAttributes redirectAttributes) {
        Long uid = userServiceImp.findUserByUserName(principal.getName()).getId();
        List<BlockEndEachStudentMeditationData> StudentData = attendanceServiceImp.ComputeBlockEC(uid, Long.valueOf(blockid));
        redirectAttributes.addFlashAttribute(StudentData);
        return "redirect:/ExtraCreditReport/blockreport";
    }

    @GetMapping("/ExtraCreditReport/blockreport")
    public String ViewblockECreport(Model model) {
        return "blockecreportpage";
    }
}
