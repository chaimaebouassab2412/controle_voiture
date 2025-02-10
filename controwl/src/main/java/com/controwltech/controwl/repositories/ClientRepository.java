package com.controwltech.controwl.repositories;


import com.controwltech.controwl.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Utilisateur, Long> {
}
