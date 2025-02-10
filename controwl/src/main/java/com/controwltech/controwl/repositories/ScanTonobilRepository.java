package com.controwltech.controwl.repositories;

import com.controwltech.controwl.entities.ScanTonobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanTonobilRepository extends JpaRepository<ScanTonobile, Long> {
}
