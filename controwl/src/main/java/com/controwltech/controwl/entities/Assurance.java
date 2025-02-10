package com.controwltech.controwl.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String compagnie;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private double montant;
    @ManyToOne()
    private Vehicule vehicule;

}
