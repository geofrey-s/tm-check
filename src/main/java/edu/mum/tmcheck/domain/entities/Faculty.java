package edu.mum.tmcheck.domain.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Faculty extends User implements Serializable {
    @ManyToOne
    Course currentCourse;

    @OneToMany
    List<Course> offeredCourses;

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
}
