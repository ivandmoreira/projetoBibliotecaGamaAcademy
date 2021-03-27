package br.com.projetofinal.bibliotecaGama.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	

	@Bean
	public boolean instantiateDatebase() throws ParseException{
		
		if(!"create".equals(strategy)) {
			return false;
		}
		
		return true;
	}
}
