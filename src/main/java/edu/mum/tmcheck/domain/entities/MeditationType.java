package edu.mum.tmcheck.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class MeditationType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;
    LocalTime startTime;
    LocalTime endTime;
}
