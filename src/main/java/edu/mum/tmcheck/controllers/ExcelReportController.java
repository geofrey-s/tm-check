package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.domain.entities.User;
import edu.mum.tmcheck.domain.repository.UserRepository;
import edu.mum.tmcheck.serviceimp.AttendanceServiceImp;
import edu.mum.tmcheck.serviceimp.BlockEndEachStudentMeditationData;
import edu.mum.tmcheck.serviceimp.ExcelReportGeneratorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students/attendancereport")
public class ExcelReportController {

    @Autowired
    AttendanceServiceImp attendanceServiceImp;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExcelReportGeneratorServiceImp excelReportGeneratorServiceImp;

    @GetMapping("/ExtraCredit/{blockid}/reportdownload/xls")
    public ResponseEntity<InputStreamResource> excelExtraCreditReport(@RequestParam("id")String userid, @PathVariable("blockid")String blockid, Model model) throws IOException {
//      Long userid = (Long) session.getAttribute("userid");
        Long uid = Long.valueOf(userid);
        List<BlockEndEachStudentMeditationData> StudentData = attendanceServiceImp.ComputeBlockEC(uid, Long.valueOf(blockid));
        ByteArrayInputStream in = excelReportGeneratorServiceImp.ExtraCreditToExcel(StudentData);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ECRecord.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

}
