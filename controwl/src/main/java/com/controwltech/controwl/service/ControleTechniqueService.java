package com.controwltech.controwl.service;

import com.controwltech.controwl.entities.ControleTechnique;
import com.controwltech.controwl.repositories.ControleTechniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControleTechniqueService {
    @Autowired
    private ControleTechniqueRepository controleTechniqueRepository;

    public List<ControleTechnique> getAllControleTechniques() {
        return controleTechniqueRepository.findAll();
    }

    public ControleTechnique getControleTechniqueById(Long id) {
        return controleTechniqueRepository.findById(id).orElse(null);
    }

    public ControleTechnique saveControleTechnique(ControleTechnique controleTechnique) {
        return controleTechniqueRepository.save(controleTechnique);
    }

    public void deleteControleTechnique(Long id) {
        controleTechniqueRepository.deleteById(id);
    }
}
