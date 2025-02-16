package com.controwltech.controwl.controller;

import com.controwltech.controwl.dto.UserManagementDTO;
import com.controwltech.controwl.dto.UserRegistrationDTO;
import com.controwltech.controwl.entities.Utilisateur;
import com.controwltech.controwl.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final UtilisateurService utilisateurService;

    @Autowired
    public AdminController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<Utilisateur>> getAllUsers() {
        List<Utilisateur> users = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserManagementDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserManagementDTO userDTO) {
        try {
            UserManagementDTO updatedUser = utilisateurService.updateUserByAdmin(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/users/admin")
    public ResponseEntity<UserManagementDTO> createAdminUser(@RequestBody UserRegistrationDTO userDTO) {
        try {
            UserManagementDTO newAdmin = utilisateurService.createAdminUser(userDTO);
            return ResponseEntity.ok(newAdmin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
} 