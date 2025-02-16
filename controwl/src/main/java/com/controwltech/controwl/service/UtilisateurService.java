package com.controwltech.controwl.service;

import com.controwltech.controwl.dto.UserLoginDTO;
import com.controwltech.controwl.dto.UserManagementDTO;
import com.controwltech.controwl.dto.UserRegistrationDTO;
import com.controwltech.controwl.entities.UserRole;
import com.controwltech.controwl.entities.Utilisateur;
import com.controwltech.controwl.mappers.UtilisateurMapper;
import com.controwltech.controwl.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    @Lazy
    private UtilisateurMapper utilisateurMapper;

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
    public Utilisateur loginUser(UserLoginDTO userDTO) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByEmail(userDTO.getEmail());
        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            // Validate password
            if (passwordEncoder.matches(userDTO.getMotDePasse(), utilisateur.getMotDePasse())) {
                return utilisateur; // Return the user if email and password match
            } else {
                throw new IllegalArgumentException("Mot de passe incorrect");
            }
        }
        throw new IllegalArgumentException("Utilisateur non trouvé"); // Throw exception if user is not found
    }


    public UserManagementDTO createAdminUser(UserRegistrationDTO userDTO) {
        if (utilisateurRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(userDTO.getNom());
        utilisateur.setEmail(userDTO.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(userDTO.getMotDePasse()));
        utilisateur.setRole(UserRole.ADMIN);
        utilisateur.setActive(true);  // Admin users are active by default

        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.UtilisateurToUserManagementDTO(savedUtilisateur);
    }

    public UserManagementDTO updateUserByAdmin(Long id, UserManagementDTO userDTO) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        // Update user details
        if (userDTO.getEmail() != null && !userDTO.getEmail().equals(utilisateur.getEmail())) {
            if (utilisateurRepository.existsByEmail(userDTO.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            utilisateur.setEmail(userDTO.getEmail());
        }
        
        if (userDTO.getNom() != null) {
            utilisateur.setNom(userDTO.getNom());
        }
        
        if (userDTO.getRole() != null) {
            utilisateur.setRole(userDTO.getRole());
        }
        
        utilisateur.setActive(userDTO.isActive());
        
        // Save updated user
        Utilisateur updatedUtilisateur = utilisateurRepository.save(utilisateur);
        
        // Convert and return updated user as DTO
        return utilisateurMapper.UtilisateurToUserManagementDTO(updatedUtilisateur);
    }
}
