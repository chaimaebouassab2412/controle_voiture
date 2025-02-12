package com.controwltech.controwl.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeService;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String description;
    @JsonIgnore
    @ManyToOne
    private Vehicule vehicule;
}
