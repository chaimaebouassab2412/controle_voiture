package com.controwltech.controwl.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Data @AllArgsConstructor  @NoArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Assurance> getAssurances() {
        return assurances;
    }

    public void setAssurances(List<Assurance> assurances) {
        this.assurances = assurances;
    }

    public List<Historique> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<Historique> historiques) {
        this.historiques = historiques;
    }

    public List<ControleTechnique> getControlesTechniques() {
        return controlesTechniques;
    }

    public void setControlesTechniques(List<ControleTechnique> controlesTechniques) {
        this.controlesTechniques = controlesTechniques;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }
}