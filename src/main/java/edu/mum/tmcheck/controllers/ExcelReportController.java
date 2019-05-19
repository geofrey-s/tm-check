package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.repository.UserRepository;
import edu.mum.tmcheck.serviceimp.AttendanceServiceImp;
import edu.mum.tmcheck.serviceimp.BlockEndEachStudentMeditationData;
import edu.mum.tmcheck.serviceimp.ExcelReportGeneratorServiceImp;
import edu.mum.tmcheck.serviceimp.UserServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
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

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/ExtraCredit/{blockid}/reportdownload/xls")
    public ResponseEntity<InputStreamResource> excelExtraCreditReport(Principal principal, @PathVariable("blockid") String blockid) throws IOException
    {
        Long userid = userServiceImp.findUserByUserName(principal.getName()).getId();
        List<BlockEndEachStudentMeditationData> StudentData = attendanceServiceImp.ComputeBlockEC(userid, Long.valueOf(blockid));
        ByteArrayInputStream in = excelReportGeneratorServiceImp.ExtraCreditToExcel(StudentData);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ECRecord.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

}
