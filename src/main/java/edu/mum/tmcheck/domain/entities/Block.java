package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    LocalDate startDate;
    LocalDate endDate;

    @OneToMany
    List<OfferedCourse> offeredCourses;
}
