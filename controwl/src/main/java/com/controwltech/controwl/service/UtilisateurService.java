package com.controwltech.controwl.service;

import com.controwltech.controwl.dto.UserLoginDTO;
import com.controwltech.controwl.dto.UserRegistrationDTO;
import com.controwltech.controwl.entities.Utilisateur;
import com.controwltech.controwl.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Encrypt passwords

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        // Encrypt password before saving
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public Optional<Utilisateur> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    // Register a new user
    public void registerUser(UserRegistrationDTO userDTO) {
        if (utilisateurRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(userDTO.getNom());
        utilisateur.setEmail(userDTO.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(userDTO.getMotDePasse())); // Encrypt password
        utilisateurRepository.save(utilisateur);
    }

    // Login user
    public boolean loginUser(UserLoginDTO userDTO) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByEmail(userDTO.getEmail());
        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            // Validate password
            return passwordEncoder.matches(userDTO.getMotDePasse(), utilisateur.getMotDePasse());
        }
        return false; // Return false if user not found or password mismatch
    }


}
