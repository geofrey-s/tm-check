package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(unique = true)
    String barcode;

    LocalDate issueDate;
    LocalDate expiryDate;
}
