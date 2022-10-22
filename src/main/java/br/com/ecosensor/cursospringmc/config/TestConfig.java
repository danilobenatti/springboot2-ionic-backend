package br.com.ecosensor.cursospringmc.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ecosensor.cursospringmc.services.DBService;

@Configuration
@Profile(value = "test")
public class TestConfig {
	
	@Bean
	public boolean instantiateDatabase(DBService dbService)
			throws ParseException {
		
		dbService.instantiateTestDatabase();
		
		return true;
	}
	
}
