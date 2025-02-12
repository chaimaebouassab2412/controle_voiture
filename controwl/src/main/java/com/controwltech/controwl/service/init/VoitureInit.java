package com.controwltech.controwl.service.init;

import com.controwltech.controwl.entities.*;
import com.controwltech.controwl.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

@Service
@AllArgsConstructor
@Transactional
public class VoitureInit {

    private final UtilisateurRepository utilisateurRepository;
    private final VehiculeRepository vehiculeRepository;
    private final AssuranceRepository assuranceRepository;
    private final ControleTechniqueRepository controleTechniqueRepository;
    private final HistoriqueRepository historiqueRepository;
    private final NotificationRepository notificationRepository;
    private final ScanTonobilRepository scanTonobilRepository;

    public void initUtilisateurs() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("John Doe");
        utilisateur.setEmail("john.doe@example.com");
        utilisateur.setMotDePasse("password");
        utilisateurRepository.save(utilisateur);

        utilisateur = new Utilisateur();
        utilisateur.setNom("Jane Smith");
        utilisateur.setEmail("jane.smith@example.com");
        utilisateur.setMotDePasse("password");
        utilisateurRepository.save(utilisateur);

        utilisateur = new Utilisateur();
        utilisateur.setNom("Alice Johnson");
        utilisateur.setEmail("alice.johnson@example.com");
        utilisateur.setMotDePasse("password");
        utilisateurRepository.save(utilisateur);
    }

    public void initVehicules() {
        utilisateurRepository.findAll().forEach(utilisateur -> {
            for (int i = 1; i <= 3; i++) {
                Vehicule vehicule = new Vehicule();
                vehicule.setMarque("Toyota" + i);
                vehicule.setModele("Corolla" + i);
                vehicule.setImmatriculation("XYZ" + i);
                vehicule.setTypeCarburant("Essence");
                vehicule.setNombrePlaces(5);
                vehicule.setPoidsAVide(1200);
                vehicule.setCapaciteReservoir(50);
                vehicule.setPuissance(130);
                vehicule.setUtilisateur(utilisateur);
                vehiculeRepository.save(vehicule);
            }
        });
    }

    public void initAssurances() {
        vehiculeRepository.findAll().forEach(vehicule -> {
            Assurance assurance = new Assurance();
            assurance.setCompagnie("AXA");
            assurance.setDateDebut(new Date());
            assurance.setDateFin(new Date());
            assurance.setMontant(5000);
            assurance.setVehicule(vehicule);
            assuranceRepository.save(assurance);
        });
    }

    public void initControlesTechniques() {
        vehiculeRepository.findAll().forEach(vehicule -> {
            ControleTechnique controle = new ControleTechnique();
            controle.setResultat("Validé");
            controle.setVehicule(vehicule);
            controleTechniqueRepository.save(controle);
        });
    }

    public void initHistoriques() {
        vehiculeRepository.findAll().forEach(vehicule -> {
            Historique historique = new Historique();
            historique.setTypeService("Révision générale");
            historique.setDate(new Date());
            historique.setDescription("Vidange et changement des filtres");
            historique.setVehicule(vehicule);
            historiqueRepository.save(historique);
        });
    }

    public void initNotifications() {
        utilisateurRepository.findAll().forEach(utilisateur -> {
            Notification notification = new Notification();
            notification.setType("Assurance");
            notification.setMessage("Votre assurance expire bientôt");
            notification.setDateEnvoi(new Date());
            notification.setUtilisateur(utilisateur);
            notificationRepository.save(notification);
        });
    }

    public void initScanTonobile() {
        vehiculeRepository.findAll().forEach(vehicule -> {
            ScanTonobile scan = new ScanTonobile();
            scan.setDocumentScanne("carte_grise.pdf");
            scan.setResultatOCR("123456ABC");
            scan.setVehicule(vehicule);
            scanTonobilRepository.save(scan);
        });
    }
}
