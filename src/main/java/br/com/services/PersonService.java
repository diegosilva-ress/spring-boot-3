package br.com.services;

import br.com.data.vo.PersonVO;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface PersonService {

  PersonVO findById(Long id);

  PagedModel<EntityModel<PersonVO>> findAll(Pageable pageable);

  PersonVO create(PersonVO personVO);

  PersonVO update(PersonVO personVO);

  void delete(Long id);

  PersonVO disablePerson(Long id);

}
