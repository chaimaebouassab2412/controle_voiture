package com.controwltech.controwl;

import com.controwltech.controwl.service.UtilisateurService;
import com.controwltech.controwl.service.init.VoitureInit;
import com.controwltech.controwl.dto.UserRegistrationDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ControwlApplication {

	private final UtilisateurService utilisateurService;
	private final VoitureInit voitureInit;

	// ✅ Constructor Injection (Best Practice)
	public ControwlApplication(UtilisateurService utilisateurService, VoitureInit voitureInit) {
		this.utilisateurService = utilisateurService;
		this.voitureInit = voitureInit;
	}

	public static void main(String[] args) {
		SpringApplication.run(ControwlApplication.class, args);
	}

	@Bean
	public CommandLineRunner initUtilisateurService() {
		return args -> {
			// ✅ Create default admin if no users exist
			if (utilisateurService.getAllUtilisateurs().isEmpty()) {
				UserRegistrationDTO adminDTO = new UserRegistrationDTO();
				adminDTO.setNom("Admin");
				adminDTO.setEmail("admin@controwl.com");
				adminDTO.setMotDePasse("adminPassword123");
				utilisateurService.createAdminUser(adminDTO);
			}

			// ✅ Initialize test data
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
