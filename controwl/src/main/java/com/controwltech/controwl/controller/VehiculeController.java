package com.controwltech.controwl.controller;

        import com.controwltech.controwl.entities.Utilisateur;
        import com.controwltech.controwl.entities.Vehicule;
        import com.controwltech.controwl.service.VehiculeService;
        import com.controwltech.controwl.service.UtilisateurService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@PreAuthorize("isAuthenticated()")
public class VehiculeController {
    private final VehiculeService vehiculeService;
    private final UtilisateurService utilisateurService;

    @Autowired
    public VehiculeController(VehiculeService vehiculeService, UtilisateurService utilisateurService) {
        this.vehiculeService = vehiculeService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/vehicules")
    public List<Vehicule> listVehicules(){

        return vehiculeService.vehiculeList();
    }
    @GetMapping
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.vehiculeList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id) {
        Vehicule vehicule = vehiculeService.getVehicleById(id);
        return vehicule != null ? ResponseEntity.ok(vehicule) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> createVehicule(@RequestBody Vehicule vehicule, @RequestParam Long utilisateurId) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(utilisateurId);
            if (utilisateur == null) {
                return ResponseEntity.badRequest()
                    .body("Utilisateur not found with id: " + utilisateurId);
            }
            
            vehicule.setUtilisateur(utilisateur);
            Vehicule savedVehicule = vehiculeService.saveVehicle(vehicule);
            return ResponseEntity.ok(savedVehicule);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body("Error creating vehicle: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
