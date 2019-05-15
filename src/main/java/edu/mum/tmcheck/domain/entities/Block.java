package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Block implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    LocalDate startDate;
    LocalDate endDate;

    @OneToMany
    List<OfferedCourse> offeredCourses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<OfferedCourse> getOfferedCourses() {
        return offeredCourses;
    }

    public void setOfferedCourses(List<OfferedCourse> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }
}