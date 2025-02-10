package com.controwltech.controwl.repositories;

import com.controwltech.controwl.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Date;
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Trouver un utilisateur par email
    Optional<Utilisateur> findByEmail(String email);

    // Vérifier si un email existe déjà (utile pour l'inscription)
    boolean existsByEmail(String email);

    // Trouver tous les utilisateurs ayant un certain nom
    List<Utilisateur> findByNomContainingIgnoreCase(String nom);
}

