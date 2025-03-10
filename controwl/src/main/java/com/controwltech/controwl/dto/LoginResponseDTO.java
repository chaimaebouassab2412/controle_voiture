package com.controwltech.controwl.dto;

public class LoginResponseDTO {
    private String token;
    private String nom;
    private String email;
    private String role;

    public LoginResponseDTO(String token, String email, String role, String nom) {
        this.token = token;
        this.nom = nom;
        this.email = email;
        this.role = role;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNom() {
        return nom;
    }

    public String setNom(String nom) {
        return this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
} 