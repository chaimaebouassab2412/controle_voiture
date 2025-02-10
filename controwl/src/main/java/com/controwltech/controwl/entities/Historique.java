package com.controwltech.controwl.entities;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeService;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String description;

    @ManyToOne
    private Vehicule vehicule;
}
