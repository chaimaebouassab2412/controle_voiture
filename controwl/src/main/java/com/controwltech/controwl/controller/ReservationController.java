package com.controwltech.controwl.controller;

import com.controwltech.controwl.dto.ReservationRequestDTO;
import com.controwltech.controwl.dto.ReservationResponseDTO;
import com.controwltech.controwl.model.ReservationStatus;
import com.controwltech.controwl.security.UserPrincipal;
import com.controwltech.controwl.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReservationResponseDTO> createReservation(
            @RequestPart("reservation") ReservationRequestDTO requestDTO,
            @RequestPart("documents") List<MultipartFile> documents,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(reservationService.createReservation(requestDTO, userPrincipal.getId(), documents));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/my-reservations")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ReservationResponseDTO>> getUserReservations(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(reservationService.getUserReservations(userPrincipal.getId()));
    }

    @PatchMapping("/{reservationId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReservationResponseDTO> updateReservationStatus(
            @PathVariable UUID reservationId,
            @RequestParam ReservationStatus status) {
        return ResponseEntity.ok(reservationService.updateReservationStatus(reservationId, status));
    }
} 