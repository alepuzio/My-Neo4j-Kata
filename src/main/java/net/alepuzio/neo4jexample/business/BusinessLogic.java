package net.alepuzio.neo4jexample.business;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import net.alepuzio.neo4jexample.model.Person;
import net.alepuzio.neo4jexample.service.PersonRepository;


public class BusinessLogic  {

	private final  Logger log = LoggerFactory.getLogger(this.getClass());

	public CommandLineRunner demo(PersonRepository personRepository) {
		return args -> {
			personRepository.deleteAll();
			Person greg = new Person("Greg");
			Person roy = new Person("Roy");
			Person craig = new Person("Craig");
			List<Person> team = Arrays.asList(greg, roy, craig);
			log.info("Before linking up with Neo4j...");
			team.stream().forEach(person -> log.info("\t" + person.toString()));
			personRepository.save(greg);
			personRepository.save(roy);
			personRepository.save(craig);
			greg = personRepository.findByName(greg.getName());
			greg.worksWith(roy);
			greg.worksWith(craig);
			personRepository.save(greg);
			roy = personRepository.findByName(roy.getName());
			roy.worksWith(craig);
			// We already know that roy works with greg
			personRepository.save(roy);
			// We already know craig works with roy and greg
			log.info("Lookup each person by name...");
			team.stream().forEach(person -> log.info(String.format("\t%s", personRepository.findByName(person.getName()).toString())));
			List<Person> teammates = personRepository.findByTeammatesName(greg.getName());
			log.info("The following have Greg as a teammate...");
			teammates.stream().forEach(person -> log.info(String.format("\t%s", person.getName())));
		};
	}


}
