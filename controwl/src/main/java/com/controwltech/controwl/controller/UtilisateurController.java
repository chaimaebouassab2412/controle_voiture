package com.controwltech.controwl.controller;

import com.controwltech.controwl.dto.UserLoginDTO;
import com.controwltech.controwl.dto.UserRegistrationDTO;
import com.controwltech.controwl.entities.Utilisateur;
import com.controwltech.controwl.service.UtilisateurService;
import io.jsonwebtoken.Claims;
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
            Utilisateur utilisateur = utilisateurService.loginUser(loginDTO);

            String token = jwtUtil.generateToken(
                    utilisateur.getEmail(),
                    utilisateur.getRole().toString()
            );

            LoginResponseDTO response = new LoginResponseDTO(
                    token,
                    utilisateur.getEmail(),
                    utilisateur.getRole().toString(),
                    utilisateur.getNom()
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
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        if (token == null || token.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            Claims claims = jwtUtil.getClaimsFromToken(token);

            String role = claims.get("role", String.class);
            if ("ADMIN".equals(role)) {
                utilisateurService.deleteUtilisateur(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
