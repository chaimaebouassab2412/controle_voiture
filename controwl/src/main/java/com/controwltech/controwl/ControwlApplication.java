package com.controwltech.controwl;

import com.controwltech.controwl.service.UtilisateurService;
import com.controwltech.controwl.service.init.VoitureInit;
import com.controwltech.controwl.dto.UserRegistrationDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class ControwlApplication {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private VoitureInit voitureInit;

	public static void main(String[] args) {
		SpringApplication.run(ControwlApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UtilisateurService utilisateurService, VoitureInit voitureInit) {
		return args -> {
			// Create default admin if no users exist
			if (utilisateurService.getAllUtilisateurs().isEmpty()) {
				UserRegistrationDTO adminDTO = new UserRegistrationDTO();
				adminDTO.setNom("Admin");
				adminDTO.setEmail("admin@controwl.com");
				adminDTO.setMotDePasse("adminPassword123");
				utilisateurService.createAdminUser(adminDTO);
			}

			// Initialize test data
			voitureInit.initUtilisateurs();
			voitureInit.initVehicules();
			voitureInit.initAssurances();
			voitureInit.initControlesTechniques();
			voitureInit.initHistoriques();
			voitureInit.initNotifications();
			voitureInit.initScanTonobile();
		};
	}
}
