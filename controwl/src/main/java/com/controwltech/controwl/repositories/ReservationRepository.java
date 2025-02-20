package com.controwltech.controwl.repositories;

import com.controwltech.controwl.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByVehicleOwnerId(UUID userId);
    List<Reservation> findByVehicleId(UUID vehicleId);
} 