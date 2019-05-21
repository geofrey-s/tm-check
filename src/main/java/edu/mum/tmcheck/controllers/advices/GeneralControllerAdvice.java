package edu.mum.tmcheck.controllers.advices;

import edu.mum.tmcheck.domain.models.MenuItem;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Set;
import java.util.TreeSet;

@ControllerAdvice
public class GeneralControllerAdvice {
    @ModelAttribute
    public void artefacts(Model model){
        model.addAttribute("appTitle", "Meditation Attendance");
        model.addAttribute("pageTitle", "Meditation Attendance");
    }

    @ModelAttribute
    public void toolbarItems(Model model){
        Set<MenuItem> navbarItems = new TreeSet<MenuItem>() {{
            add(new MenuItem("1", "Reports", "/reports/entry-attendance-report").setActive(true));
            add(new MenuItem("2", "TM Editor", "/attendance/editor"));
        }};

        model.addAttribute("navbarItems", navbarItems);
    }
}
