package br.com.services.impl;

import br.com.data.vo.v1.PersonVO;
import br.com.exceptions.ResourceNotFoundException;
import br.com.mapper.ModelMapperUtil;
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

  public PersonVO findById(Long id) {
    logger.info("Finding one person");
    var entity = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

    return ModelMapperUtil.parseObject(entity, PersonVO.class);
  }

  public List<PersonVO> findAll() {
    logger.info("Finding all people");
    return ModelMapperUtil.parseListObjects(personRepository.findAll(), PersonVO.class);
  }

  public PersonVO create(PersonVO personVO) {
    logger.info("Creating a new person");
    var entity = ModelMapperUtil.parseObject(personVO, Person.class);
    return ModelMapperUtil.parseObject(personRepository.save(entity), PersonVO.class);
  }

  public PersonVO update(PersonVO personVO) {
    logger.info("Updating a new person");
    var entity = personRepository.findById(personVO.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

    entity.setFirstName(personVO.getFirstName());
    entity.setLastName(personVO.getLastName());
    entity.setAddress(personVO.getAddress());
    entity.setGender(personVO.getGender());

    return ModelMapperUtil.parseObject(personRepository.save(entity), PersonVO.class);
  }

  public void delete(Long id) {
    logger.info("Deleting a person");

    var entity = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

    personRepository.delete(entity);
  }

}
