package br.com.controllers;

import br.com.data.vo.v1.PersonVO;
import br.com.services.PersonService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public PersonVO findById(@PathVariable("id") Long id) {
    return this.personService.findById(id);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<PersonVO> findAll() {
    return this.personService.findAll();
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public PersonVO create(@RequestBody PersonVO personVO) {
    return this.personService.create(personVO);
  }

  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public PersonVO update(@RequestBody PersonVO personVO) {
    return this.personService.update(personVO);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    this.personService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
