package net.alepuzio.neo4jexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import net.alepuzio.neo4jexample.business.BusinessLogic;
import net.alepuzio.neo4jexample.service.PersonRepository;

@SpringBootApplication
@EnableNeo4jRepositories
public class AccessingDataNeo4jApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AccessingDataNeo4jApplication.class, args);
		System.exit(0);
	}

	@Bean
	CommandLineRunner demo(PersonRepository personRepository) {
		return new BusinessLogic().demo(personRepository);
	}
}

