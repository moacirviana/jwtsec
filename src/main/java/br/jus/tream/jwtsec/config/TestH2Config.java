package br.jus.tream.jwtsec.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.jus.tream.jwtsec.services.DBServiceTest;

@Configuration
@Profile("h2")
public class TestH2Config {
	@Autowired
	private DBServiceTest dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (!"create".equals(this.strategy)) {
			return false;
		}
		    dbService.instantiateTestDatabase();
		return true;
	}
	
}
