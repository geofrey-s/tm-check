package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;

@Entity
public class Attendence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @JoinColumn(name = "location_id")
    Location location;

    @ManyToOne
    @JoinColumn(name = "meditation_type_id")
    MeditationType meditationType;

    @ManyToOne
    @JoinColumn(name = "tm_type_id")
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
