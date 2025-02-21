package com.controwltech.controwl.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor  @NoArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ✅ Changed to use Long ID with auto-increment
    private Long id;  // ✅ Changed from UUID to Long

    private String marque;
    private String modele;
    private String numeroChassis;
    private String typeCarburant;
    private int nombrePlaces;
    private double poidsAVide;
    private double capaciteReservoir;
    private double puissance;
    private double kilometrage;
    private String couleur;
    private int annee;
    private String immatriculation;
    private String statut;

    @JsonIgnore
    @ManyToOne
    private Utilisateur utilisateur;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private List<Assurance> assurances;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private List<Historique> historiques;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private List<ControleTechnique> controlesTechniques;

    @Temporal(TemporalType.DATE)
    private Date dateAchat;
}
