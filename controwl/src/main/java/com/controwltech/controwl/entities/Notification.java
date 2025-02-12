package com.controwltech.controwl.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnvoi;
    @JsonIgnore
    @ManyToOne
    private Utilisateur utilisateur;
}
