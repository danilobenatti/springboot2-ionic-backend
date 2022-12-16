package br.com.ecosensor.cursospringmc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ecosensor.cursospringmc.services.DBService;
import br.com.ecosensor.cursospringmc.services.EmailService;
import br.com.ecosensor.cursospringmc.services.SmtpEmailService;

@Configuration
@Profile(value = "dev")
public class DevConfig {
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	boolean instantiateDatabase(DBService dbService)
			throws ParseException {
		
		if ("create".equals(strategy) || "create-drop".equals(strategy)) {
			dbService.instantiateTestDatabase();
			return true;
		}
		return false;
		
	}
	
	@Bean
	EmailService emailService() {
		return new SmtpEmailService();
	}
	
}
