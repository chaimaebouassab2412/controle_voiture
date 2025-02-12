package com.controwltech.controwl;

import com.controwltech.controwl.entities.Utilisateur;
import com.controwltech.controwl.entities.Vehicule;
import com.controwltech.controwl.repositories.UtilisateurRepository;
import com.controwltech.controwl.repositories.VehiculeRepository;
import com.controwltech.controwl.service.VehiculeService;
import com.controwltech.controwl.service.init.VoitureInit;
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
	CommandLineRunner start(VoitureInit voitureInit) {
		return args -> {
			voitureInit.initUtilisateurs();
			voitureInit.initVehicules();
			voitureInit.initAssurances();
			voitureInit.initControlesTechniques();
			voitureInit.initHistoriques();
			voitureInit.initNotifications();
			voitureInit.initScanTonobile();
	};
}}
