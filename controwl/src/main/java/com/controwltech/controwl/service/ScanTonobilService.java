package com.controwltech.controwl.service;

import com.controwltech.controwl.entities.ScanTonobile;
import com.controwltech.controwl.repositories.ScanTonobilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScanTonobilService {
    @Autowired
    private ScanTonobilRepository scanTonobilRepository;

    public List<ScanTonobile> getAllScans() {
        return scanTonobilRepository.findAll();
    }

    public ScanTonobile getScanById(Long id) {
        return scanTonobilRepository.findById(id).orElse(null);
    }

    public ScanTonobile saveScan(ScanTonobile scanTonobil) {
        return scanTonobilRepository.save(scanTonobil);
    }

    public void deleteScan(Long id) {
        scanTonobilRepository.deleteById(id);
    }
}
