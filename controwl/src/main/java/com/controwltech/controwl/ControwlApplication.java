package com.controwltech.controwl;

import com.controwltech.controwl.entities.Utilisateur;
import com.controwltech.controwl.entities.Vehicule;
import com.controwltech.controwl.repositories.UtilisateurRepository;
import com.controwltech.controwl.service.VehiculeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ControwlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControwlApplication.class, args);
	}

	@Bean
	CommandLineRunner start(VehiculeService vehiculeService, UtilisateurRepository utilisateurRepository) {
		return args -> {
			// Add Users (Utilisateurs) if they don't exist
			if (!utilisateurRepository.existsById(1L)) {
				Utilisateur user1 = new Utilisateur();
				user1.setNom("Youssef");
				user1.setEmail("youssef@example.com");
				user1.setMdp("password123");
				utilisateurRepository.save(user1);
			}

			if (!utilisateurRepository.existsById(2L)) {
				Utilisateur user2 = new Utilisateur();
				user2.setNom("Amina");
				user2.setEmail("amina@example.com");
				user2.setMdp("securepassword");
				utilisateurRepository.save(user2);
			}

			// Fetch a user to associate with vehicles
			Utilisateur proprietaire = utilisateurRepository.findById(1L).orElseThrow(() ->
					new RuntimeException("Utilisateur not found"));

			// Add Vehicles (Vehicules)
			Stream.of(
					new Object[]{"Mercedes", "Classe A", "Essence"},
					new Object[]{"BMW", "Série 3", "Diesel"},
					new Object[]{"RANGE", "Evoque", "Essence"}
			).forEach(data -> {
				Vehicule vehicule = new Vehicule();
				vehicule.setMarque((String) data[0]);
				vehicule.setModele((String) data[1]);
				vehicule.setTypeCarburant((String) data[2]);

				// Associate user with the vehicle
				vehicule.setProprietaire(proprietaire);

				vehiculeService.addVehicule(vehicule);
			});
		};
	}
}
