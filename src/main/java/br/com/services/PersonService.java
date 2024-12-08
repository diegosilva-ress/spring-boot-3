package br.com.services;

import br.com.data.vo.PersonVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {

  PersonVO findById(Long id);

  Page<PersonVO> findAll(Pageable pageable);

  PersonVO create(PersonVO personVO);

  PersonVO update(PersonVO personVO);

  void delete(Long id);

  PersonVO disablePerson(Long id);

}
