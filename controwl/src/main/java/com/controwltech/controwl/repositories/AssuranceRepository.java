package com.controwltech.controwl.repositories;

import com.controwltech.controwl.entities.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {

    // Récupérer toutes les assurances d'un véhicule
    List<Assurance> findByVehiculeId(Long idVehicule);

    // Trouver les assurances qui expirent bientôt
    List<Assurance> findByDateFinBefore(Date dateLimite);

    // Vérifier si un véhicule a une assurance active
    boolean existsByVehiculeIdAndDateFinAfter(Long idVehicule, Date dateActuelle);
}
