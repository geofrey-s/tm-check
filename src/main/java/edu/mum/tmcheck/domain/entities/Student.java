package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Student extends User implements Serializable {
    @ManyToOne
    @Valid
    Entry entry;

    @Column(unique = true)
    @NotEmpty
    String studentRegId;

    @NotNull
    LocalDate departureDate;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    List<OfferedCourse> enrolledCourses;

    @OneToOne(mappedBy = "student")
    @Valid
    Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

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
