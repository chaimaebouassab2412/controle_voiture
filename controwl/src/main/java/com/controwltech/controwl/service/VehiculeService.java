package com.controwltech.controwl.service;

import com.controwltech.controwl.entities.Vehicule;
import com.controwltech.controwl.repositories.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculeService {
    @Autowired
    private VehiculeRepository vehiculeRepository;

    public String addVehicule(Vehicule vehicule) {
        vehiculeRepository.save(vehicule);
        return "Succeed";
    }

    public Vehicule getVehicleById(Long id) {
        return (Vehicule) vehiculeRepository.findById(id).orElse(null);
    }

    public Vehicule saveVehicle(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public void deleteVehicle(Long id) {
        vehiculeRepository.deleteById(id);
    }

    public List<Vehicule> vehiculeList() {
        return vehiculeRepository.findAll();
    }
}
