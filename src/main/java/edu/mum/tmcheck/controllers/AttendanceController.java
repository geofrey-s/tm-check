package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.domain.entities.Location;
import edu.mum.tmcheck.domain.entities.MeditationType;
import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.domain.models.MeditationAttendanceEditor;
import edu.mum.tmcheck.domain.repository.LocationRepository;
import edu.mum.tmcheck.serviceimp.AttendanceServiceImp;
import edu.mum.tmcheck.serviceimp.LocationServiceImp;
import edu.mum.tmcheck.serviceimp.MeditationTypeServiceImp;
import edu.mum.tmcheck.serviceimp.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    MeditationTypeServiceImp meditationTypeServiceImp;

    @Autowired
    StudentServiceImp studentServiceImp;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LocationServiceImp locationServiceImp;

    @Autowired
    AttendanceServiceImp attendanceServiceImp;

    @GetMapping("/editor")
    public String editor(@ModelAttribute("editor") MeditationAttendanceEditor editor, Model model) {
        editor.setCreatedAt(LocalDate.now().toString());
        model.addAttribute("pageTitle", "TM Editor");
        model.addAttribute("meditationtypes", meditationTypeServiceImp.findAllByNameExcept("standard"));
        model.addAttribute("locations", locationServiceImp.findAll());

        return "tm-editor";
    }

    @PostMapping("/editor/save")
    public String editorSave(@ModelAttribute MeditationAttendanceEditor editor, Model model, RedirectAttributes redirectAttributes) {
        Attendance attendance = attendanceServiceImp.createFromEditor(editor);

        redirectAttributes.addFlashAttribute(attendance);
        return "redirect:/attendance/editor";
    }

    @PostMapping("/editor/upload")
    @ResponseBody
    public String editorFileUpload(@ModelAttribute MultipartFile file, Model model) throws IOException {
        attendanceServiceImp.processFileUpload(file);
        return "";
    }

    @GetMapping("/getstudentretreatattendanceform")
    public String retrievetmdataforstudentform(Model model) {
        List<MeditationType> meditationTypes = meditationTypeServiceImp.findAll().stream().filter(m -> !m.getName().equalsIgnoreCase("standard")).collect(Collectors.toList());
        HashMap<Long, String> types = new HashMap<>();
        meditationTypes.forEach(m -> {
            types.put(m.getId(), m.getName());
        });
        model.addAttribute("meditationtypes", types);
        return "getretrievelform";
    }

    @GetMapping("/retrieveTMCheckorRetreatAttendance")
    public String retrievetmcheckorretreat(@RequestParam("studentid") String studentid, @RequestParam("meditationtype") String meditationtypeid, Model model) {
        System.out.println(studentid + " -------------------------------------" + meditationtypeid);
        List<Attendance> studentattendance = (List<Attendance>) attendanceServiceImp.findTMCheckRecord(studentid, Long.valueOf(meditationtypeid));
        model.addAttribute("studentattendance", studentattendance);
        return "ViewTMAttendace";
    }

    @DeleteMapping("/{attendanceId}")
    public String deleteUser(@PathVariable String attendaceId) {
        attendanceServiceImp.deletebyid(Long.valueOf(attendaceId));
        return "viewattedancepage";
    }

    @GetMapping("/tmsviewavedattendancepage")
    public String viewsavedattendace(Model model) {
        return "viewsavedattendancepage";
    }

}
