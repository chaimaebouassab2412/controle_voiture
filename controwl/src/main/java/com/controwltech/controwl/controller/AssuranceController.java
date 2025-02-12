package com.controwltech.controwl.controller;

import com.controwltech.controwl.entities.Assurance;
import com.controwltech.controwl.service.AssuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assurances")
public class AssuranceController {
    @Autowired
    private AssuranceService assuranceService;

    @GetMapping("all")
    public List<Assurance> getAllAssurances() {
        return assuranceService.getAllAssurances();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assurance> getAssuranceById(@PathVariable Long id) {
        Assurance assurance = assuranceService.getAssuranceById(id);
        return assurance != null ? ResponseEntity.ok(assurance) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Assurance createAssurance(@RequestBody Assurance assurance) {
        return assuranceService.saveAssurance(assurance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssurance(@PathVariable Long id) {
        assuranceService.deleteAssurance(id);
        return ResponseEntity.noContent().build();
    }
}
