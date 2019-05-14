package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import javax.validation.Constraint;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    long id;

    String name;

    @Column(unique = true)
    String username;

    String password;
}
