package com.controwltech.controwl.controller;

import com.controwltech.controwl.entities.ScanTonobile;
import com.controwltech.controwl.service.ScanTonobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scan-tonobil")
public class ScanTonobilController {

    @Autowired
    private ScanTonobilService scanTonobilService;

    @GetMapping
    public List<ScanTonobile> getAllScans() {
        return scanTonobilService.getAllScans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScanTonobile> getScanById(@PathVariable Long id) {
        ScanTonobile scanTonobil = scanTonobilService.getScanById(id);
        return scanTonobil != null ? ResponseEntity.ok(scanTonobil) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ScanTonobile createScan(@RequestBody ScanTonobile scanTonobil) {
        return scanTonobilService.saveScan(scanTonobil);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScan(@PathVariable Long id) {
        scanTonobilService.deleteScan(id);
        return ResponseEntity.noContent().build();
    }
}
