package edu.mum.tmcheck.domain.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class OfferedCourse {
    @ManyToOne
    Course course;

    @ManyToOne
    Faculty faculty;

    @ManyToOne
    Block block;

    List<Student> students;
}
