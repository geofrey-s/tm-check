package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"student_id","meditation_type_id", "location_id", "created_at"})
})
public class Attendance implements Serializable {
    @Transient
    public static final String DEFAULT_MEDITATION_TYPE = "AM";

    @Transient
    public static final String DEFAULT_LOCATION_CODE = "DB";

    @Transient
    public static final String DEFAULT_TM_TYPE = "Standard";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "created_at")
    LocalDate createdAt;

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

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
