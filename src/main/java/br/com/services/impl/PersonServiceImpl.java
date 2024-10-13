package br.com.services.impl;

import br.com.exceptions.ResourceNotFoundException;
import br.com.model.Person;
import br.com.repository.PersonRepository;
import br.com.services.PersonService;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  private Logger logger = Logger.getLogger(PersonService.class.getName());

  public PersonServiceImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Person findById(Long id) {
    logger.info("Finding one person");
    return personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
  }

  public List<Person> findAll() {
    logger.info("Finding all people");
    return personRepository.findAll();
  }

  public Person create(Person person) {
    logger.info("Creating a new person");
    return personRepository.save(person);
  }

  public Person update(Person person) {
    logger.info("Updating a new person");
    var entity = personRepository.findById(person.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    return personRepository.save(person);
  }

  public void delete(Long id) {
    logger.info("Deleting a person");

    var entity = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

    personRepository.delete(entity);
  }

}
