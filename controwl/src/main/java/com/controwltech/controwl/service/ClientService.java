package com.controwltech.controwl.service;

import com.controwltech.controwl.entities.Utilisateur;
import com.controwltech.controwl.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Utilisateur> getAllClients() {
        return clientRepository.findAll();
    }

    public Utilisateur getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Utilisateur saveClient(Utilisateur client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
