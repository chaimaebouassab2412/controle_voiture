package com.controwltech.controwl.controller;

import com.controwltech.controwl.entities.TableauDeBord;
import com.controwltech.controwl.service.TableauDeBordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tableau-de-bord")
public class TableauDeBordController {

    @Autowired
    private TableauDeBordService tableauDeBordService;

    @GetMapping
    public List<TableauDeBord> getAllTableaux() {
        return tableauDeBordService.getAllTableaux();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableauDeBord> getTableauById(@PathVariable Long id) {
        TableauDeBord tableau = tableauDeBordService.getTableauById(id);
        return tableau != null ? ResponseEntity.ok(tableau) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public TableauDeBord createTableau(@RequestBody TableauDeBord tableauDeBord) {
        return tableauDeBordService.saveTableau(tableauDeBord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableau(@PathVariable Long id) {
        tableauDeBordService.deleteTableau(id);
        return ResponseEntity.noContent().build();
    }
}
