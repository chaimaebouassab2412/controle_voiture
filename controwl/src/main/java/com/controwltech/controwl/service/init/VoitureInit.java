package com.controwltech.controwl.service.init;

import com.controwltech.controwl.entities.*;
import com.controwltech.controwl.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

@Service
@Transactional
public class VoitureInit {

    private final UtilisateurRepository utilisateurRepository;
    private final VehiculeRepository vehiculeRepository;
    private final AssuranceRepository assuranceRepository;
    private final ControleTechniqueRepository controleTechniqueRepository;
    private final HistoriqueRepository historiqueRepository;
    private final NotificationRepository notificationRepository;
    private final ScanTonobilRepository scanTonobilRepository;

    // Constructor for dependency injection
    public VoitureInit(UtilisateurRepository utilisateurRepository,
                       VehiculeRepository vehiculeRepository,
                       AssuranceRepository assuranceRepository,
                       ControleTechniqueRepository controleTechniqueRepository,
                       HistoriqueRepository historiqueRepository,
                       NotificationRepository notificationRepository,
                       ScanTonobilRepository scanTonobilRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.assuranceRepository = assuranceRepository;
        this.controleTechniqueRepository = controleTechniqueRepository;
        this.historiqueRepository = historiqueRepository;
        this.notificationRepository = notificationRepository;
        this.scanTonobilRepository = scanTonobilRepository;
    }

    public void initUtilisateurs() {
        Utilisateur user1 = new Utilisateur();
        user1.setNom("John Doe");
        user1.setEmail("john.doey@example.com");
        user1.setMotDePasse("passwkgord123");
        utilisateurRepository.save(user1);

        Utilisateur user2 = new Utilisateur();
        user2.setNom("salmaaa Smith");
        user2.setEmail("salmaa.smith@example.com");
        user2.setMotDePasse("pa58ssword456");
        utilisateurRepository.save(user2);

        Utilisateur user3 = new Utilisateur();
        user3.setNom("ahmed Johnson");
        user3.setEmail("alice.johnson@example.com");
        user3.setMotDePasse("password789");
        utilisateurRepository.save(user3);
    }

    public void initVehicules() {
        utilisateurRepository.findAll().forEach(user -> {
            for (int i = 1; i <= 2; i++) {
                Vehicule vehicule = new Vehicule();
                vehicule.setMarque("Toyota");
                vehicule.setModele("Corolla " + i);
                vehicule.setImmatriculation("ABC-123" + i);
                vehicule.setTypeCarburant("Essence");
                vehicule.setNombrePlaces(5);
                vehicule.setUtilisateur(user);
                vehiculeRepository.save(vehicule);
            }
        });
    }

    public void initAssurances() {
        vehiculeRepository.findAll().forEach(vehicule -> {
            Assurance assurance = new Assurance();
            assurance.setCompagnie("AXA");
            assurance.setDateDebut(new Date());
            assurance.setDateFin(new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000)); // +1 year
            assurance.setMontant(2000.0);
            assurance.setVehicule(vehicule);
            assuranceRepository.save(assurance);
        });
    }

    public void initControlesTechniques() {
        vehiculeRepository.findAll().forEach(vehicule -> {
            ControleTechnique controleTechnique = new ControleTechnique();
            controleTechnique.setResultat("Validé");
            controleTechnique.setVehicule(vehicule);
            controleTechniqueRepository.save(controleTechnique);
        });
    }

    public void initHistoriques() {
        vehiculeRepository.findAll().forEach(vehicule -> {
            Historique historique = new Historique();
            historique.setTypeService("Révision générale");
            historique.setDate(new Date());
            historique.setDescription("Vidange et changement de filtres");
            historique.setVehicule(vehicule);
            historiqueRepository.save(historique);
        });
    }

    public void initNotifications() {
        utilisateurRepository.findAll().forEach(user -> {
            Notification notification = new Notification();
            notification.setType("Assurance");
            notification.setMessage("Votre assurance arrive bientôt à expiration.");
            notification.setDateEnvoi(new Date());
            notification.setUtilisateur(user);
            notificationRepository.save(notification);
        });
    }

    public void initScanTonobile() {
        vehiculeRepository.findAll().forEach(vehicule -> {
            ScanTonobile scan = new ScanTonobile();
            scan.setDocumentScanne("carte_grise.pdf");
            scan.setResultatOCR("XYZ-123456");
            scan.setVehicule(vehicule);
            scanTonobilRepository.save(scan);
        });
    }
}
