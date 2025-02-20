package com.controwltech.controwl.service;

import com.controwltech.controwl.dto.ReservationRequestDTO;
import com.controwltech.controwl.dto.ReservationResponseDTO;
import com.controwltech.controwl.exceptions.ResourceNotFoundException;
import com.controwltech.controwl.entities.*;
import com.controwltech.controwl.repositories.ReservationRepository;
import com.controwltech.controwl.repositories.UtilisateurRepository;
import com.controwltech.controwl.repositories.VehiculeRepository;
import com.controwltech.controwl.repositories.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import com.controwltech.controwl.model.ReservationStatus;
import com.controwltech.controwl.dto.DocumentDTO;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final VehiculeRepository vehiculeRepository;
    private final DocumentRepository documentRepository;
    private final DocumentService documentService;

    @Transactional
    public ReservationResponseDTO createReservation(ReservationRequestDTO requestDTO, UUID userId, List<MultipartFile> documents) {
        Utilisateur user = utilisateurRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            
        Vehicule vehicle = vehiculeRepository.findById(requestDTO.getVehicleId())
            .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        if (!vehicle.getProprietaire().getId().equals(userId)) {
            throw new AccessDeniedException("You can only make reservations for your own vehicles");
        }

        Reservation reservation = new Reservation();
        reservation.setVehicleOwner(user);
        reservation.setVehicle(vehicle);
        reservation.setInspectionDate(requestDTO.getInspectionDate());
        reservation.setComments(requestDTO.getComments());
        
        Reservation savedReservation = reservationRepository.save(reservation);

        // Handle document uploads
        if (documents != null && !documents.isEmpty()) {
            documents.forEach(file -> documentService.saveDocument(file, savedReservation));
        }

        return mapToDTO(savedReservation);
    }

    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
            .map(this::mapToDTO)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> getUserReservations(UUID userId) {
        return reservationRepository.findByVehicleOwnerId(userId).stream()
            .map(this::mapToDTO)
            .toList();
    }

    @Transactional
    public ReservationResponseDTO updateReservationStatus(UUID reservationId, ReservationStatus status) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
            
        reservation.setStatus(status);
        return mapToDTO(reservationRepository.save(reservation));
    }

    private ReservationResponseDTO mapToDTO(Reservation reservation) {
        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setId(reservation.getId());
        dto.setVehicleOwnerId(reservation.getVehicleOwner().getId());
        dto.setVehicleOwnerName(reservation.getVehicleOwner().getNom() + " " + reservation.getVehicleOwner().getPrenom());
        dto.setVehicleId(reservation.getVehicle().getId());
        dto.setVehicleInfo(reservation.getVehicle().getMarque() + " " + reservation.getVehicle().getModele());
        dto.setInspectionDate(reservation.getInspectionDate());
        dto.setStatus(reservation.getStatus());
        dto.setCreatedAt(reservation.getCreatedAt());
        dto.setComments(reservation.getComments());
        if (reservation.getDocuments() != null) {
            dto.setDocuments(reservation.getDocuments().stream()
                .map(this::mapDocumentToDTO)
                .toList());
        }
        return dto;
    }

    private DocumentDTO mapDocumentToDTO(Document document) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(document.getId());
        dto.setFileName(document.getFileName());
        dto.setFileType(document.getFileType());
        dto.setUploadedAt(document.getUploadedAt());
        return dto;
    }
} 