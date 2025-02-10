package com.controwltech.controwl.repositories;


import com.controwltech.controwl.entities.ControleTechnique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControleTechniqueRepository extends JpaRepository<ControleTechnique, Long> {
}
