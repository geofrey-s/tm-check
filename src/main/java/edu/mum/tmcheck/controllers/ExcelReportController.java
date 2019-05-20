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
import java.util.Optional;

@RestController
@RequestMapping("/download")
public class ExcelReportController {

    @Autowired
    AttendanceServiceImp attendanceServiceImp;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExcelReportGeneratorServiceImp excelReportGeneratorServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/ec-attendance-report/{blockid}.xlsx")
    public ResponseEntity<InputStreamResource> excelExtraCreditReport(Principal principal, @PathVariable("blockid") String blockid) throws IOException
    {
        Long userid = userServiceImp.findUserByUserName(principal.getName()).getId();
        List<BlockEndEachStudentMeditationData> StudentData = attendanceServiceImp.ComputeBlockEC(userid, Long.valueOf(blockid));
        ByteArrayInputStream in = excelReportGeneratorServiceImp.ExtraCreditToExcel(StudentData);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ec-attendance-report.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

    @GetMapping("/entry-attendance-report/{entryId}.xls")
    public ResponseEntity<InputStreamResource> entryAttendanceReport(Principal principal, @PathVariable("entryId") Optional<Long> entryId) throws IOException
    {
        return null;
    }

    @GetMapping("/block-attendance-report/{blockId}.xls")
    public ResponseEntity<InputStreamResource> blockAttendanceReport(Principal principal, @PathVariable("blockId") Optional<Long> blockId) throws IOException
    {
        return null;
    }
}
