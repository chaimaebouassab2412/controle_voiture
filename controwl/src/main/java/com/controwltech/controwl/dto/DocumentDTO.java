package com.controwltech.controwl.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DocumentDTO {
    private UUID id;
    private String fileName;
    private String fileType;
    private LocalDateTime uploadedAt;
} 