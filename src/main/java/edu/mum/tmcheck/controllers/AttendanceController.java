package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.domain.models.MeditationAttendanceEditor;
import edu.mum.tmcheck.domain.repository.LocationRepository;
import edu.mum.tmcheck.serviceimp.AttendanceServiceImp;
import edu.mum.tmcheck.serviceimp.LocationServiceImp;
import edu.mum.tmcheck.serviceimp.MeditationTypeServiceImp;
import edu.mum.tmcheck.serviceimp.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.time.LocalDate;

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
    public String editorSave(@PathParam ("isEdit") boolean isEdit, @Valid @ModelAttribute("editor") MeditationAttendanceEditor editor, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "TM Editor");
            model.addAttribute("meditationtypes", meditationTypeServiceImp.findAllByNameExcept("standard"));
            model.addAttribute("locations", locationServiceImp.findAll());
            return "tm-editor";
        }

        Attendance attendance = attendanceServiceImp.createFromEditor(editor);
        redirectAttributes.addFlashAttribute(attendance);
        return isEdit ? "redirect:/attendance/list" : "redirect:/attendance/editor";
    }

    @GetMapping("/editor/edit/{id}")
    public String editorEdit(@PathVariable long id, @ModelAttribute("editor") MeditationAttendanceEditor editor, Model model) {
        model.addAttribute("pageTitle", "TM Editor");
        model.addAttribute("meditationtypes", meditationTypeServiceImp.findAllByNameExcept("standard"));
        model.addAttribute("locations", locationServiceImp.findAll());

        editor = attendanceServiceImp.editorFromRecordById(id);
        model.addAttribute("editor", editor);
        model.addAttribute("isEdit", true);

        return "tm-editor";
    }

    @GetMapping("/editor/remove/{id}")
    public String editorSave(@PathVariable long id) {
        attendanceServiceImp.removeById(id);

        return "redirect:/attendance/list";
    }

    @PostMapping("/editor/upload")
    @ResponseBody
    public String editorFileUpload(@ModelAttribute MultipartFile file) throws IOException {
        attendanceServiceImp.processFileUpload(file);
        return "";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("pageTitle", "TM Viewer");
        model.addAttribute("reportData", attendanceServiceImp.findAllExceptOrderedByIdDesc("standard"));

        return "tm-viewer";
    }
}
