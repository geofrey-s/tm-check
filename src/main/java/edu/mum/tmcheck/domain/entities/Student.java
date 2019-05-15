package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Student extends User {

    @ManyToOne
    @JoinColumn(name = "entry_id")
    Entry entry;

    @Column(unique = true)
    String studentId;

    LocalDate departureDate;

    @OneToMany
    List<OfferedCourse> enrolledCourses;
}
