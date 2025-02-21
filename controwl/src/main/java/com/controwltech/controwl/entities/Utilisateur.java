package com.controwltech.controwl.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utilisateurs") // ✅ Explicit table name
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Changed to Long ID with auto-increment
    private Long id;  // Changed from UUID to Long

    private String nom;
    private String email;

    @JsonIgnore
    private String motDePasse; // ✅ Marked as @JsonIgnore to prevent exposure in API responses

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vehicule> vehicules = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER; // Default role

    private boolean isActive = false; // Account verification status

    // ✅ Constructor without parameters
    public Utilisateur() {}

    // ✅ Constructor with parameters
    public Utilisateur(String nom, String email, String motDePasse) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    // ✅ Getters and Setters (Manually Added)

    public Long getId() {  // Changed return type from UUID to Long
        return id;
    }

    public void setId(Long id) {  // Changed parameter type from UUID to Long
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<Vehicule> getVehicules() {
        return vehicules;
    }

    public void setVehicules(List<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
