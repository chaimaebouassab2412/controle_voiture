package com.controwltech.controwl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data @AllArgsConstructor @Setter @Getter
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marque;
    private String modele;
    private String numeroChassis;
    private String typeCarburant;
    private int nombrePlaces;
    private double poidsAVide;
    private double capaciteReservoir;
    private double puissance;

    @ManyToOne
    private Utilisateur proprietaire;
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private List<Assurance> assurances;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private List<Historique> historiques;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private List<ControleTechnique> controlesTechniques;

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getNumeroChassis() {
        return numeroChassis;
    }

    public void setNumeroChassis(String numeroChassis) {
        this.numeroChassis = numeroChassis;
    }

    public String getTypeCarburant() {
        return typeCarburant;
    }

    public void setTypeCarburant(String typeCarburant) {
        this.typeCarburant = typeCarburant;
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public double getPoidsAVide() {
        return poidsAVide;
    }

    public void setPoidsAVide(double poidsAVide) {
        this.poidsAVide = poidsAVide;
    }

    public double getCapaciteReservoir() {
        return capaciteReservoir;
    }

    public void setCapaciteReservoir(double capaciteReservoir) {
        this.capaciteReservoir = capaciteReservoir;
    }

    public double getPuissance() {
        return puissance;
    }

    public void setPuissance(double puissance) {
        this.puissance = puissance;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    private double kilometrage;
    private String couleur;
    private int annee;
    private String immatriculation;
    private String statut;


    public void setProprietaire(Utilisateur utilisateurById) {
    }
}