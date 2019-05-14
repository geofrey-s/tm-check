package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;

    @Column(unique = true)
    String code;

    @OneToMany
    List<OfferedCourse> offeredCourses;
}
