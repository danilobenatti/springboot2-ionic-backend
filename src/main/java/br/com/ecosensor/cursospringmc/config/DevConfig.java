package br.com.ecosensor.cursospringmc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ecosensor.cursospringmc.services.DBService;

@Configuration
@Profile(value = "dev")
public class DevConfig {
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase(DBService dbService)
			throws ParseException {
		
		if ("create".equals(strategy) || "create-drop".equals(strategy)) {
			dbService.instantiateTestDatabase();
			return true;
		}
		return false;
		
	}
	
}