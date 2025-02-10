package com.controwltech.controwl.service;

import com.controwltech.controwl.entities.Historique;
import com.controwltech.controwl.repositories.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriqueService {
    @Autowired
    private HistoriqueRepository historiqueRepository;

    public List<Historique> getAllHistories() {
        return historiqueRepository.findAll();
    }

    public Historique getHistoriqueById(Long id) {
        return historiqueRepository.findById(id).orElse(null);
    }

    public Historique saveHistorique(Historique historique) {
        return historiqueRepository.save(historique);
    }

    public void deleteHistorique(Long id) {
        historiqueRepository.deleteById(id);
    }
}
