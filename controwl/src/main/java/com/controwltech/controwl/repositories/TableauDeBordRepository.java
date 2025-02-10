package com.controwltech.controwl.repositories;

import com.controwltech.controwl.entities.TableauDeBord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableauDeBordRepository extends JpaRepository<TableauDeBord, Long> {
    // Tu peux ajouter des méthodes personnalisées si nécessaire
}
