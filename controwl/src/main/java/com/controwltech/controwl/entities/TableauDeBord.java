package com.controwltech.controwl.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class TableauDeBord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Utilisateur utilisateur;
    @JsonIgnore
    @OneToMany
    private List<Notification> listNotifications;
    @JsonIgnore
    @OneToMany
    private List<Vehicule> listVehicles;
}
