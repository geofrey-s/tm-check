package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.domain.entities.Entry;
import edu.mum.tmcheck.domain.models.MenuItem;
import edu.mum.tmcheck.serviceimp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.format.TextStyle;
import java.util.*;

@Controller
@RequestMapping(value = "/reports")
public class ReportController {
    @Autowired
    EntryServiceImp entryServiceImp;

    @Autowired
    BlockServiceImp blockServiceImp;

    @Autowired
    EntryAttendanceReportServiceImp entryAttendanceReportServiceImp;

    AttendanceServiceImp attendanceServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @ModelAttribute
    public void prepareReportsLists(Model model) {
        Map<String, String> reports = new HashMap<String, String>() {{
            put("Entry Attendance Report", "/reports/entry-attendance-report");
            put("Extra Credit Attendance Report", "/reports/ec-attendance-report");
            put("Block Attendance Report", "/reports/block-attendance-report");
        }};

        model.addAttribute("availableReports", reports);

        Set<MenuItem> navbarItems = new TreeSet<MenuItem>() {{
            add(new MenuItem("1", "Reports", "/reports/entry-attendance-report").setActive(true));
            add(new MenuItem("2", "TM Editor", "/tm-editor"));
        }};

        model.addAttribute("navbarItems", navbarItems);
    }

    @GetMapping(value = "/entry-attendance-report")
    public String entryAttendanceReport(Model model) {
        String reportTitle = "Entry Attendance Report";
        String reportKey = "entry-attendance-report";

        Map<String, String> reports = (HashMap<String, String>) model.asMap().get("availableReports");
        reports.remove(reportTitle);

        model.addAttribute("pageTitle", reportTitle);
        model.addAttribute("reportTitle", reportTitle);
        model.addAttribute("reportKey", reportKey);

        List<Entry> entries = entryServiceImp.findAll();
        model.addAttribute("entries", entries);

        // @todo make dynamic depending on the selected entry
        model.addAttribute("reportData", entryAttendanceReportServiceImp.generateByEntry(entries.get(0).getName()));

        return "entry-attendance-report";
    }

    @GetMapping(value = "/ec-attendance-report")
    public String ecAttendanceReport(Model model) {
        String reportTitle = "EC Attendance Report";
        String reportKey = "ec-attendance-report";

        Map<String, String> reports = (HashMap<String, String>) model.asMap().get("availableReports");
        reports.remove(reportTitle);

        model.addAttribute("pageTitle", reportTitle);
        model.addAttribute("reportTitle", reportTitle);
        model.addAttribute("reportKey", reportKey);

        return "ec-attendance-report";
    }

    @GetMapping(value = "/block-attendance-report")
    public String blockAttendanceReport(Model model) {
        String reportTitle = "Block Attendance Report";
        String reportKey = "block-attendance-report";


        Map<String, String> reports = (HashMap<String, String>) model.asMap().get("availableReports");
        reports.remove(reportTitle);

        model.addAttribute("pageTitle", reportTitle);
        model.addAttribute("reportTitle", reportTitle);
        model.addAttribute("reportKey", reportKey);

        return "block-attendance-report";

    }

    @GetMapping("/ExtraCredit")
    public String ExtraCreditReport(Principal principal, Model model) {
        Long userid = userServiceImp.findUserByUserName(principal.getName()).getId();
        HashMap<Long, String> blocks = new HashMap<>();
        List<Block> facultyblocks = blockServiceImp.getfacultyteachingblocks(userid);
        facultyblocks.forEach(blk -> {
            blocks.put(blk.getId(), blk.getStartDate().plusDays(10).getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + blk.getStartDate().plusDays(10).getYear());
        });
        model.addAttribute("facultyblocks", blocks);
        return "BlockECReportFormPage";
    }

    @GetMapping("/ProcessExtraCreditforstudents")
    public String GetStudetsExtraCredit(@RequestParam("block") String blockid, Principal principal, Model model) {
        Long uid = userServiceImp.findUserByUserName(principal.getName()).getId();
        List<BlockEndEachStudentMeditationData> StudentData = attendanceServiceImp.ComputeBlockEC(uid, Long.valueOf(blockid));
        model.addAttribute("StudentsData", StudentData);
        model.addAttribute("blockid", blockid);
        return "blockecreportpage";
    }
}
