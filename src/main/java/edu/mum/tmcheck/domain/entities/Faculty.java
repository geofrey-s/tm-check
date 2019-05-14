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

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }

    public List<Course> getOfferedCourses() {
        return offeredCourses;
    }

    public void setOfferedCourses(List<Course> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }

    @OneToMany
    List<Course> offeredCourses;
}
