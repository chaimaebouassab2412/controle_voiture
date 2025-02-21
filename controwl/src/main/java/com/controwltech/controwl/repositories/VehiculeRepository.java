package com.controwltech.controwl.repositories;

import com.controwltech.controwl.entities.Vehicule;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.controwltech.controwl.entities.Utilisateur;

import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.UUID;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, UUID> {
    //@Query("SELECT u FROM Utilisateur u WHERE u.id = :id")
    //Utilisateur findUtilisateurById(@Param("id") Long id);


    // Trouver un véhicule par son numéro de chassis
    Optional<Vehicule> findByNumeroChassis(String numeroChassis);

    // Récupérer les véhicules d'un utilisateur
    //List<Vehicule> findByUt(Long id);

    // Lister tous les véhicules d'une marque spécifique
    List<Vehicule> findByMarqueIgnoreCase(String marque);

    // Trouver les véhicules dont la date d'achat est avant une certaine date
    List<Vehicule> findByDateAchatBefore(Date date);

    Optional<Object> findById(Long id);

    void deleteById(Long id);
}

