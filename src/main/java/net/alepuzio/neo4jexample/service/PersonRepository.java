package net.alepuzio.neo4jexample.service;

import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import net.alepuzio.neo4jexample.model.Person;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

  Person findByName(String name);
  List<Person> findByTeammatesName(String name);
}