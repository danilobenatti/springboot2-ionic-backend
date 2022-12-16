package br.com.ecosensor.cursospringmc.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ecosensor.cursospringmc.services.DBService;
import br.com.ecosensor.cursospringmc.services.EmailService;
import br.com.ecosensor.cursospringmc.services.MockMailService;

@Configuration
@Profile(value = "test")
public class TestConfig {
	
	@Bean
	boolean instantiateDatabase(DBService dbService) throws ParseException {
		
		dbService.instantiateTestDatabase();
		
		return true;
	}
	
	@Bean
	EmailService emailService() {
		return new MockMailService();
	}
	
}
