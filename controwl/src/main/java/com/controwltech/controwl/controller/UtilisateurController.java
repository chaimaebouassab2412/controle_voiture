package com.controwltech.controwl.controller;

import com.controwltech.controwl.dto.UserLoginDTO;
import com.controwltech.controwl.dto.UserRegistrationDTO;
import com.controwltech.controwl.entities.Utilisateur;
import com.controwltech.controwl.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.controwltech.controwl.entities.Utilisateur;
import com.controwltech.controwl.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.controwltech.controwl.security.JwtUtil;
import com.controwltech.controwl.dto.LoginResponseDTO;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, JwtUtil jwtUtil) {
        this.utilisateurService = utilisateurService;
        this.jwtUtil = jwtUtil;
    }

    // Get all users
    @GetMapping("/users")
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        return utilisateur != null ? ResponseEntity.ok(utilisateur) : ResponseEntity.notFound().build();
    }

    // Register a new user
    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userDTO) {
        try {
            utilisateurService.registerUser(userDTO);
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Login user
    // Login endpoint without JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO loginDTO) {
        try {
            Utilisateur utilisateur = utilisateurService.authenticateUser(loginDTO);
            
            String token = jwtUtil.generateToken(
                utilisateur.getEmail(), 
                utilisateur.getRole().toString()
            );
            
            LoginResponseDTO response = new LoginResponseDTO(
                token,
                utilisateur.getEmail(),
                utilisateur.getRole().toString()
            );
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
        }
    }

    // Logout user
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Logout logic, if applicable
        return ResponseEntity.ok("Logout successful");
    }

    // Add a new user (existing logic)
    @PostMapping("/add")
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.saveUtilisateur(utilisateur);
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}
