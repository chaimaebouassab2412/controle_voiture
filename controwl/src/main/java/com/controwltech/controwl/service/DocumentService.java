package com.controwltech.controwl.service;

import com.controwltech.controwl.entities.Document;
import com.controwltech.controwl.entities.Reservation;
import com.controwltech.controwl.repositories.DocumentRepository;
import com.controwltech.controwl.exception.FileStorageException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Value("${app.file-storage-location}")
    private String fileStorageLocation;

    public Document saveDocument(MultipartFile file, Reservation reservation) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        
        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
            Path targetLocation = Paths.get(fileStorageLocation).resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            Document document = new Document();
            document.setFileName(fileName);
            document.setFileType(file.getContentType());
            document.setFilePath(targetLocation.toString());
            document.setReservation(reservation);

            return documentRepository.save(document);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
} 