package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.domain.entities.Location;
import edu.mum.tmcheck.domain.entities.MeditationType;
import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.domain.repository.LocationRepository;
import edu.mum.tmcheck.serviceimp.AttendanceServiceImp;
import edu.mum.tmcheck.serviceimp.MeditationTypeServiceImp;
import edu.mum.tmcheck.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/Student")
public class AttendanceController {
    @Autowired
    MeditationTypeServiceImp meditationTypeServiceImp;

    @Autowired
    StudentService studentService;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    AttendanceServiceImp attendanceServiceImp;

    @GetMapping("/tmretreatandcheckform")
    public String Gettmcheckform(Model model) {
        model.addAttribute("meditationtypes", meditationTypeServiceImp.findAll());
        return "tmretreatandcheckformpage";
    }

    @GetMapping("/retrieveTMCheckorRetreatAttendance")
    public String retrievetmcheckorretreat(@RequestParam("studentid") String studentid, @RequestParam("meditationtype") String meditationtypeid, Model model) {
        List<Attendance> studentattendance = (List<Attendance>) attendanceServiceImp.findTMCheckRecord(Long.valueOf(studentid), Long.valueOf(meditationtypeid));
        model.addAttribute("studentattendance", studentattendance);
        return "ViewTMAttendace";
    }

    @DeleteMapping("/{attendanceId}")
    public String deleteUser(@PathVariable String attendaceId) {
        attendanceServiceImp.deletebyid(Long.valueOf(attendaceId));
        return "viewattedancepage";
    }
    @PostMapping("/savestudentdata")
    public String SaveTmRetreatData(@RequestParam("StudentID") Long StudentID, @RequestParam("TMCheckDate") String TMCheckDate, @RequestParam("TMCheckType")Long TMCheckType, Model model, RedirectAttributes redirectAttributes)
    {
        Student student = studentService.findByID(StudentID);
        LocalDate tmattendancedate = LocalDate.parse(TMCheckDate);
        MeditationType meditationType = meditationTypeServiceImp.findById(TMCheckType);
        Location location;
        if(meditationType.getName().equalsIgnoreCase("retreat"))
            location = locationRepository.findByName("Men's Dome");
        else
            location = locationRepository.findByName("Dalby Hall");

        Attendance studentattendance = new Attendance();
        studentattendance.setCreatedAt(tmattendancedate);
        studentattendance.setStudent(student);
        studentattendance.setMeditationType(meditationType);
        studentattendance.setLocation(location);

        attendanceServiceImp.save(studentattendance);
        redirectAttributes.addFlashAttribute(studentattendance);
        return "redirect:/tmsavedattendsancepage";
    }

    @GetMapping("/tmsviewavedattendancepage")
    public String viewsavedattendace(Model model){
        return "viewsavedattendancepage";
    }

}
