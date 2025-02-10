package com.controwltech.controwl.controller;

        import com.controwltech.controwl.entities.Vehicule;
        import com.controwltech.controwl.service.VehiculeService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {
    @Autowired
    private VehiculeService vehiculeService;
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
    public Vehicule createVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.saveVehicle(vehicule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
