package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Faculty extends User implements Serializable {
    @OneToMany
    @JoinColumn(name = "faculty_id")
    List<OfferedCourse> offeredCourses;

    public List<OfferedCourse> getOfferedCourses() {
        return offeredCourses;
    }

    public void setOfferedCourses(List<OfferedCourse> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }
}
