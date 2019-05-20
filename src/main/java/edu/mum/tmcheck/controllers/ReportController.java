package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.domain.entities.Entry;
import edu.mum.tmcheck.domain.entities.User;
import edu.mum.tmcheck.domain.models.MenuItem;
import edu.mum.tmcheck.serviceimp.*;
import edu.mum.tmcheck.services.ExcelReportGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    AttendanceServiceImp attendanceServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    BlockAttendanceReportServiceImp blockAttendanceReportServiceImp;

    @Autowired
    ExcelReportGeneratorService excelReportGeneratorService;

    @Autowired
    AuthenticationServiceImp authenticationServiceImp;

    @ModelAttribute
    public void prepareReportsLists(Model model) {
        Map<String, String> reports = new HashMap<String, String>() {{
            put("Entry Attendance Report", "/reports/entry-attendance-report");
            put("Extra Credit Attendance Report", "/reports/ec-attendance-report");
        }};

        model.addAttribute("availableReports", reports);

        Set<MenuItem> navbarItems = new TreeSet<MenuItem>() {{
            add(new MenuItem("1", "Reports", "/reports/entry-attendance-report").setActive(true));
            add(new MenuItem("2", "TM Editor", "/tm-editor"));
        }};

        model.addAttribute("navbarItems", navbarItems);
    }

    @GetMapping(value = {"/entry-attendance-report/{entryId}", "/entry-attendance-report"})
    public String entryAttendanceReport(@PathVariable(name = "entryId", required = false) Optional<Long> entryId, Model model) {
        String reportTitle = "Entry Attendance Report";
        String reportKey = "entry-attendance-report";

        Entry currentEntry = entryServiceImp.findById(entryId.orElse(1L));
        model.addAttribute("currentEntry", currentEntry);
        model.addAttribute("downloadLink", entryAttendanceReportServiceImp.downloadLink(currentEntry.getId()));

        Map<String, String> reports = (HashMap<String, String>) model.asMap().get("availableReports");
        reports.remove(reportTitle);

        model.addAttribute("pageTitle", reportTitle);
        model.addAttribute("reportTitle", reportTitle);
        model.addAttribute("reportKey", reportKey);

        List<Entry> entries = entryServiceImp.findAll();
        model.addAttribute("entries", entries);

        model.addAttribute("reportData", entryAttendanceReportServiceImp.generateByEntry(currentEntry.getName()));

        return "entry-attendance-report";
    }

    @GetMapping(value = {"/ec-attendance-report/{blockId}", "/ec-attendance-report"})
    public String ecAttendanceReport(@PathVariable(name = "blockId", required = false) Optional<Long> blockId, Model model, Principal principal) throws Exception{
        if (principal.getName() == null)
            return "redirect:/login";

        User user = authenticationServiceImp.getAuthenticatedUserByUsername(principal.getName());

        String reportTitle = "Extra Credit Attendance Report";
        String reportKey = "ec-attendance-report";

        Map<String, String> reports = (HashMap<String, String>) model.asMap().get("availableReports");
        reports.remove(reportTitle);

        model.addAttribute("pageTitle", reportTitle);
        model.addAttribute("reportTitle", reportTitle);
        model.addAttribute("reportKey", reportKey);

        List<Block> blocks = blockServiceImp.findAllByUserId(user.getId());
        model.addAttribute("blocks", blocks);

        long defaultBlockId = blocks.get(0) != null ? blocks.get(0).getId() : 0;
        Block currentBlock = blockServiceImp.findById(blockId.orElse(defaultBlockId));
        model.addAttribute("currentBlock", currentBlock);
        // model.addAttribute("downloadLink", blo.downloadLink(currentBlock.getId()));

        model.addAttribute("reportData", attendanceServiceImp.ComputeBlockEC(user.getId(), currentBlock.getId()));
        model.addAttribute("blockId", blockId);

        return "ec-attendance-report";
    }

    @GetMapping(value = {"/block-attendance-report/{blockId}", "/block-attendance-report"})
    public String blockAttendanceReport(@PathVariable(name = "blockId", required = false) Optional<Long> blockId, Model model) {
        Map<String, String> reports = (HashMap<String, String>) model.asMap().get("availableReports");
        reports.remove(BlockAttendanceReportServiceImp.REPORT_TITLE);

        Block currentBlock = blockServiceImp.findById(blockId.orElse(1L));
        model.addAttribute("currentBlock", currentBlock);
        model.addAttribute("downloadLink", blockAttendanceReportServiceImp
                .downloadLink(currentBlock.getId()));

        model.addAttribute("pageTitle", BlockAttendanceReportServiceImp.REPORT_TITLE);
        model.addAttribute("reportTitle", BlockAttendanceReportServiceImp.REPORT_TITLE);
        model.addAttribute("reportKey", BlockAttendanceReportServiceImp.REPORT_ID);

        List<Block> blocks = blockServiceImp.findAll();
        model.addAttribute("blocks", blocks);

        model.addAttribute("reportData", blockAttendanceReportServiceImp
                .findByBlock(currentBlock.getStartDate(), currentBlock.getEndDate()));

        return "block-attendance-report";
    }

    @GetMapping("/ExtraCredit")
    public String ExtraCreditReport(Principal principal, Model model) {
        Long userid = userServiceImp.findUserByUserName(principal.getName()).getId();
        HashMap<Long, String> blocks = new HashMap<>();
        List<Block> facultyblocks = blockServiceImp.findAllByUserId(userid);
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
