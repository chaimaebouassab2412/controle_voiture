package com.controwltech.controwl.entities;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ScanTonobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentScanne;
    private String resultatOCR;

    @ManyToOne
    private Vehicule vehicule;
}
