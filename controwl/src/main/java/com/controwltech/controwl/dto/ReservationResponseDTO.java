package com.controwltech.controwl.dto;

import com.controwltech.controwl.model.ReservationStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ReservationResponseDTO {
    private UUID id;
    private UUID vehicleOwnerId;
    private String vehicleOwnerName;
    private UUID vehicleId;
    private String vehicleInfo;
    private LocalDateTime inspectionDate;
    private ReservationStatus status;
    private List<DocumentDTO> documents;
    private LocalDateTime createdAt;
    private String comments;
} 