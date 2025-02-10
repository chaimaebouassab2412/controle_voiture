package com.controwltech.controwl.service;

import com.controwltech.controwl.entities.Assurance;
import com.controwltech.controwl.repositories.AssuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssuranceService {
    @Autowired
    private AssuranceRepository assuranceRepository;

    public List<Assurance> getAllAssurances() {
        return assuranceRepository.findAll();
    }

    public Assurance getAssuranceById(Long id) {
        return assuranceRepository.findById(id).orElse(null);
    }

    public Assurance saveAssurance(Assurance assurance) {
        return assuranceRepository.save(assurance);
    }

    public void deleteAssurance(Long id) {
        assuranceRepository.deleteById(id);
    }
}
