package br.com.services.impl;

import br.com.controllers.PersonController;
import br.com.data.vo.PersonVO;
import br.com.exceptions.ResourceNotFoundException;
import br.com.mapper.ModelMapperUtil;
import br.com.model.Person;
import br.com.repository.PersonRepository;
import br.com.services.PersonService;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  private final Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());

  public PersonServiceImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public PersonVO findById(Long id) {
    logger.info("Finding one person");
    var entity = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

    var personVO = ModelMapperUtil.parseObject(entity, PersonVO.class);
    personVO.add(
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).findById(id))
            .withSelfRel());

    return personVO;
  }

  public List<PersonVO> findAll() {
    logger.info("Finding all people");
    var people = ModelMapperUtil.parseListObjects(personRepository.findAll(), PersonVO.class);

    people.forEach(p -> p.add(WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

    return people;
  }

  public PersonVO create(PersonVO personVO) {
    logger.info("Creating a new person");
    var entity = ModelMapperUtil.parseObject(personVO, Person.class);
    var vo = ModelMapperUtil.parseObject(personRepository.save(entity), PersonVO.class);

    vo.add(WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

    return vo;
  }

  public PersonVO update(PersonVO personVO) {
    logger.info("Updating a new person");
    var entity = personRepository.findById(personVO.getKey())
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

    entity.setFirstName(personVO.getFirstName());
    entity.setLastName(personVO.getLastName());
    entity.setAddress(personVO.getAddress());
    entity.setGender(personVO.getGender());

    var vo = ModelMapperUtil.parseObject(personRepository.save(entity), PersonVO.class);

    vo.add(WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

    return vo;
  }

  public void delete(Long id) {
    logger.info("Deleting a person");

    var entity = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

    personRepository.delete(entity);
  }

}
