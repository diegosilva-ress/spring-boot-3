package br.com.services;

import br.com.data.vo.PersonVO;
import java.util.List;

public interface PersonService {

  PersonVO findById(Long id);

  List<PersonVO> findAll();

  PersonVO create(PersonVO personVO);

  PersonVO update(PersonVO personVO);

  void delete(Long id);

}
