package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    @Column(unique = true)
    String code;


    @OneToMany()
    @JoinColumn(name = "course_id")
    List<OfferedCourse> offeredCourses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<OfferedCourse> getOfferedCourses() {
        return offeredCourses;
    }

    public void setOfferedCourses(List<OfferedCourse> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }
}
