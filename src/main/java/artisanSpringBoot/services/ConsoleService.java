package artisanSpringBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import artisanSpringBoot.model.Civilite;
import artisanSpringBoot.model.Formateur;

@Service
public class ConsoleService implements CommandLineRunner {

	@Autowired
	PersonneRestCallService service;

	@Override
	public void run(String... args) throws Exception {
		service.findAll();
	}

}


