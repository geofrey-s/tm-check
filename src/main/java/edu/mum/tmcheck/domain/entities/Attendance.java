package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;

@Entity
public class Attendance {
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
}
