package com.controwltech.controwl.entities;
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

    @OneToMany
    private List<Notification> listNotifications;

    @OneToMany
    private List<Vehicule> listVehicles;
}
