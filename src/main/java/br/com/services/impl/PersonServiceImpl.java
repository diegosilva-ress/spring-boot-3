package br.com.services.impl;

import br.com.controllers.PersonController;
import br.com.data.vo.PersonVO;
import br.com.exceptions.RequiredObjectIsNullException;
import br.com.exceptions.ResourceNotFoundException;
import br.com.mapper.ModelMapperUtil;
import br.com.model.Person;
import br.com.repository.PersonRepository;
import br.com.services.PersonService;
import java.util.logging.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  private final PagedResourcesAssembler<PersonVO> assembler;

  private final Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());

  public PersonServiceImpl(PersonRepository personRepository, PagedResourcesAssembler<PersonVO> assembler) {
    this.personRepository = personRepository;
    this.assembler = assembler;
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

  public PagedModel<EntityModel<PersonVO>> findAll(Pageable pageable) {
    logger.info("Finding all people");

    var personPage = personRepository.findAll(pageable);

    var personVoPage = personPage.map(p -> ModelMapperUtil.parseObject(p, PersonVO.class));

    personVoPage.map(p -> p.add(WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

    Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class)
        .findAll(pageable.getPageNumber(), pageable.getPageSize(), "ASC")).withSelfRel();

    return assembler.toModel(personVoPage, link);
  }

  public PersonVO create(PersonVO personVO) {
    if (personVO == null) {
      throw new RequiredObjectIsNullException();
    }
    logger.info("Creating a new person");
    var entity = ModelMapperUtil.parseObject(personVO, Person.class);
    var vo = ModelMapperUtil.parseObject(personRepository.save(entity), PersonVO.class);

    vo.add(WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

    return vo;
  }

  public PersonVO update(PersonVO personVO) {
    if (personVO == null) {
      throw new RequiredObjectIsNullException();
    }
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

  @Override
  @Transactional
  public PersonVO disablePerson(Long id) {
    logger.info("Disabling one person");

    personRepository.disablePerson(id);

    var entity = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

    var personVO = ModelMapperUtil.parseObject(entity, PersonVO.class);
    personVO.add(
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).findById(id))
            .withSelfRel());

    return personVO;
  }

}
