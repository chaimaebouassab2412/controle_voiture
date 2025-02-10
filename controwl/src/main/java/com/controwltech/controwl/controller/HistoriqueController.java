package com.controwltech.controwl.controller;

import com.controwltech.controwl.entities.Historique;
import com.controwltech.controwl.service.HistoriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiques")
public class HistoriqueController {
    @Autowired
    private HistoriqueService historiqueService;

    @GetMapping
    public List<Historique> getAllHistoriques() {
        return historiqueService.getAllHistories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historique> getHistoriqueById(@PathVariable Long id) {
        Historique historique = historiqueService.getHistoriqueById(id);
        return historique != null ? ResponseEntity.ok(historique) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Historique createHistorique(@RequestBody Historique historique) {
        return historiqueService.saveHistorique(historique);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorique(@PathVariable Long id) {
        historiqueService.deleteHistorique(id);
        return ResponseEntity.noContent().build();
    }
}
