package com.controwltech.controwl.controller;

import com.controwltech.controwl.entities.ControleTechnique;
import com.controwltech.controwl.service.ControleTechniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controle-technique")
public class ControleTechniqueController {

    @Autowired
    private ControleTechniqueService controleTechniqueService;

    @GetMapping
    public List<ControleTechnique> getAllControleTechniques() {
        return controleTechniqueService.getAllControleTechniques();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ControleTechnique> getControleTechniqueById(@PathVariable Long id) {
        ControleTechnique controleTechnique = controleTechniqueService.getControleTechniqueById(id);
        return controleTechnique != null ? ResponseEntity.ok(controleTechnique) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ControleTechnique createControleTechnique(@RequestBody ControleTechnique controleTechnique) {
        return controleTechniqueService.saveControleTechnique(controleTechnique);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteControleTechnique(@PathVariable Long id) {
        controleTechniqueService.deleteControleTechnique(id);
        return ResponseEntity.noContent().build();
    }
}
