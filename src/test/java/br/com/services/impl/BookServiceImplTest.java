package br.com.services.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.data.vo.BookVO;
import br.com.exceptions.RequiredObjectIsNullException;
import br.com.model.Book;
import br.com.repository.BookRepository;
import br.com.unittests.mapper.mocks.MockBook;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

  MockBook input;

  @InjectMocks
  BookServiceImpl service;

  @Mock
  BookRepository repository;

  @BeforeEach
  void setUp() {
    input = new MockBook();
  }

  @Test
  void findById() {
    Book entity = input.mockEntity(1);
    entity.setId(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(entity));

    var result = service.findById(1L);

    assertNotNull(result);
    assertNotNull(result.getKey());
    assertNotNull(result.getLinks());
    assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
  }

  @Test
  void findAll() {
    List<Book> list = input.mockEntityList();

    when(repository.findAll()).thenReturn(list);

    var bookVOList = service.findAll();

    assertNotNull(bookVOList);
    assertEquals(14, bookVOList.size());
  }

  @Test
  void create() {
    Book persisted = input.mockEntity(1);
    persisted.setId(1L);

    BookVO vo = input.mockVO(1);
    vo.setKey(1L);

    when(repository.save(any(Book.class))).thenReturn(persisted);

    var result = service.create(vo);

    assertNotNull(result);
    assertNotNull(result.getKey());
    assertNotNull(result.getLinks());
    assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
  }

  @Test
  void update() {
    Book entity = input.mockEntity(1);
    entity.setId(1L);

    Book persisted = input.mockEntity(1);
    persisted.setId(1L);

    BookVO vo = input.mockVO(1);
    vo.setKey(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(entity));
    when(repository.save(any(Book.class))).thenReturn(persisted);

    var result = service.update(vo);

    assertNotNull(result);
    assertNotNull(result.getKey());
    assertNotNull(result.getLinks());
    assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
  }

  @Test
  void delete() {
    Book entity = input.mockEntity(1);
    entity.setId(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(entity));

    assertDoesNotThrow(() -> service.delete(1L));
  }

  @Test
  void createWithNullBook() {
    Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.create(null));
    String expectedMessage = "It's not allowed to persist a null object!";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void updateWithNullBook() {
    Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.update(null));
    String expectedMessage = "It's not allowed to persist a null object!";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }
}