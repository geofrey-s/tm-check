package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Student extends User implements Serializable {
    @ManyToOne
    Entry entry;

    @Column(unique = true)
    String studentRegId;

    LocalDate departureDate;


    @ManyToMany(cascade = { CascadeType.ALL})
    List<OfferedCourse> enrolledCourses;

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public String getStudentRegId() {
        return studentRegId;
    }

    public void setStudentRegId(String studentRegId) {
        this.studentRegId = studentRegId;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public List<OfferedCourse> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<OfferedCourse> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
