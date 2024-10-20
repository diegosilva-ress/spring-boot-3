package br.com.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.data.vo.PersonVO;
import br.com.model.Person;
import br.com.repository.PersonRepository;
import br.com.services.impl.PersonServiceImpl;
import br.com.unittests.mapper.mocks.MockPerson;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

  MockPerson input;

  @InjectMocks
  PersonServiceImpl service;

  @Mock
  PersonRepository repository;

  @BeforeEach
  void setUp() {
    input = new MockPerson();
  }

  @Test
  void findById() {
    Person entity = input.mockEntity(1);
    entity.setId(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(entity));

    var result = service.findById(1L);

    assertNotNull(result);
    assertNotNull(result.getKey());
    assertNotNull(result.getLinks());
    assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
  }

  @Test
  void findAll() {
  }

  @Test
  void create() {
    Person persisted = input.mockEntity(1);
    persisted.setId(1L);

    PersonVO vo = input.mockVO(1);
    vo.setKey(1L);

    when(repository.save(any(Person.class))).thenReturn(persisted);

    var result = service.create(vo);

    assertNotNull(result);
    assertNotNull(result.getKey());
    assertNotNull(result.getLinks());
    assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
  }

  @Test
  void update() {
    Person entity = input.mockEntity(1);
    entity.setId(1L);

    Person persisted = input.mockEntity(1);
    persisted.setId(1L);

    PersonVO vo = input.mockVO(1);
    vo.setKey(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(entity));
    when(repository.save(any(Person.class))).thenReturn(persisted);

    var result = service.update(vo);

    assertNotNull(result);
    assertNotNull(result.getKey());
    assertNotNull(result.getLinks());
    assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
  }

  @Test
  void delete() {
    Person entity = input.mockEntity(1);
    entity.setId(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(entity));

    assertDoesNotThrow(() -> service.delete(1L));
  }
}