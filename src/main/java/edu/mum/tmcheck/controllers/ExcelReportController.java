package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.reports.ECAttendanceReport;
import edu.mum.tmcheck.domain.reports.EntryAttendanceReport;
import edu.mum.tmcheck.domain.repository.UserRepository;
import edu.mum.tmcheck.serviceimp.*;

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

    @Autowired
    EntryAttendanceReportServiceImp entryAttendanceReportServiceImp;

    @Autowired
    ECAttendanceReportServiceImp ecAttendanceReportServiceImp;

    @GetMapping("/ec-attendance-report/{blockid}.xlsx")
    public ResponseEntity<InputStreamResource> excelExtraCreditReport(Principal principal, @PathVariable("blockid") Long blockid) throws IOException
    {
        Long userid = userServiceImp.findUserByUserName(principal.getName()).getId();
        List<ECAttendanceReport> StudentData = ecAttendanceReportServiceImp.findAllByFacultyIdAndBlockId(userid, blockid);
        ByteArrayInputStream in = excelReportGeneratorServiceImp.ExtraCreditToExcel(StudentData);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ec-attendance-report.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

    @GetMapping("/entry-attendance-report/{entryId}.xlsx")
    public ResponseEntity<InputStreamResource> entryAttendanceReport(Principal principal, @PathVariable("entryId") Optional<Long> entryId) throws IOException
    {
        Long entryid = entryId.get();
        List<EntryAttendanceReport> EntryAttendanceReport =  entryAttendanceReportServiceImp.generateByEntryId(entryid);
        ByteArrayInputStream in = excelReportGeneratorServiceImp.EntryMeditationAttendanceReportToExcel(EntryAttendanceReport);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachement; filename=EntryAttendanceReport.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}
