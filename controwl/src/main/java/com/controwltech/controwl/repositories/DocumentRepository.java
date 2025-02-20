package com.controwltech.controwl.repositories;

import com.controwltech.controwl.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {
    List<Document> findByReservationId(UUID reservationId);
} 