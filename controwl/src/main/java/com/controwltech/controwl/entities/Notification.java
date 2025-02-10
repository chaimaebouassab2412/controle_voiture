package com.controwltech.controwl.entities;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnvoi;

    @ManyToOne
    private Utilisateur utilisateur;
}
