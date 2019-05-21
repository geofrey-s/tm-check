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
    public String editorSave(@ModelAttribute MeditationAttendanceEditor editor, RedirectAttributes redirectAttributes) {
        Attendance attendance = attendanceServiceImp.createFromEditor(editor);

        redirectAttributes.addFlashAttribute(attendance);
        return "redirect:/attendance/editor";
    }

    @PostMapping("/editor/upload")
    @ResponseBody
    public String editorFileUpload(@ModelAttribute MultipartFile file) throws IOException {
        attendanceServiceImp.processFileUpload(file);
        return "";
    }
}
