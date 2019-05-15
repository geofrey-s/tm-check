package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Attendance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne
    Student student;

    @ManyToOne
    Location location;

    @ManyToOne
    MeditationType meditationType;

    @ManyToOne
    TMType tmType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public MeditationType getMeditationType() {
        return meditationType;
    }

    public void setMeditationType(MeditationType meditationType) {
        this.meditationType = meditationType;
    }

    public TMType getTmType() {
        return tmType;
    }

    public void setTmType(TMType tmType) {
        this.tmType = tmType;
    }
}
