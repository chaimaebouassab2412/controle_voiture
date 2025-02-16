import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.controwltech.controwl.service.UtilisateurService;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;
    private final UtilisateurService utilisateurService;

    public VehiculeController(VehiculeService vehiculeService, UtilisateurService utilisateurService) {
        this.vehiculeService = vehiculeService;
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public ResponseEntity<Vehicule> createVehicule(@RequestBody Vehicule vehicule, @RequestParam Long utilisateurId) {
        try {
            Utilisateur utilisateur = utilisateurService.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found with id: " + utilisateurId));
            
            vehicule.setUtilisateur(utilisateur);
            Vehicule savedVehicule = vehiculeService.save(vehicule);
            return ResponseEntity.ok(savedVehicule);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ... existing code ...
} 