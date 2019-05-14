package edu.mum.tmcheck.domain.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Faculty extends User {
    @ManyToOne
    @JoinColumn(name = "current_course_id")
    Course currentCourse;

    @OneToMany
    List<Course> offeredCourses;
}
