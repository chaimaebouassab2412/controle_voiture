package com.controwltech.controwl.service;

import com.controwltech.controwl.entities.Vehicule;
import com.controwltech.controwl.repositories.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehiculeService {
    @Autowired
    private VehiculeRepository vehiculeRepository;

    public String addVehicule(Vehicule vehicule) {
        vehiculeRepository.save(vehicule);
        return "Succeed";
    }

    public Vehicule getVehicleById(UUID id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    public Vehicule saveVehicle(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public void deleteVehicle(UUID id) {
        vehiculeRepository.deleteById(id);
    }

    public List<Vehicule> vehiculeList() {
        return vehiculeRepository.findAll();
    }
}
