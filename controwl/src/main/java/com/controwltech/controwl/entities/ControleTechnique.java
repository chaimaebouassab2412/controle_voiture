package com.controwltech.controwl.entities;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


import jakarta.persistence.*;

@Entity
public class ControleTechnique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resultat;

    @ManyToOne
    private Vehicule vehicule;
}
