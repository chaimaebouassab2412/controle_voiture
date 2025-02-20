package com.controwltech.controwl.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReservationRequestDTO {
    private UUID vehicleId;
    private LocalDateTime inspectionDate;
    private String comments;
} 