package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Student extends User{

    @ManyToOne
    @JoinColumn(name = "entry_id")
    Entry entry;

    @Column(unique = true)
    String studentId;

    LocalDate departureDate;

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    @OneToMany
    List<OfferedCourse> enrolledCourses;
}
