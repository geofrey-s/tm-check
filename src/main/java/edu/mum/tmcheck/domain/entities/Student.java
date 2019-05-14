package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;

    @ManyToOne
    @JoinColumn(name = "entry_id")
    Entry entry;

    @Column(unique = true)
    String studentId;

    LocalDate departedDate;
}
