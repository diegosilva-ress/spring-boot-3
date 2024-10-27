package br.com.unittests.mapper.mocks;

import br.com.data.vo.BookVO;
import br.com.model.Book;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

  public Book mockEntity() {
    return mockEntity(0);
  }

  public BookVO mockVO() {
    return mockVO(0);
  }

  public List<Book> mockEntityList() {
    List<Book> books = new ArrayList<>();
    for (int i = 0; i < 14; i++) {
      books.add(mockEntity(i));
    }
    return books;
  }

  public List<BookVO> mockVOList() {
    List<BookVO> persons = new ArrayList<>();
    for (int i = 0; i < 14; i++) {
      persons.add(mockVO(i));
    }
    return persons;
  }

  public Book mockEntity(Integer number) {
    Book book = new Book();
    book.setTitle("Title Test" + number);
    book.setPrice(Double.valueOf(number));
    book.setAuthor("Name Test" + number);
    book.setLaunchDate(Date.from(Instant.now()));
    book.setId(number.longValue());
    return book;
  }

  public BookVO mockVO(Integer number) {
    BookVO bookVO = new BookVO();
    bookVO.setTitle("Title Test" + number);
    bookVO.setPrice(Double.valueOf(number));
    bookVO.setAuthor("Name Test" + number);
    bookVO.setLaunchDate(Date.from(Instant.now()));
    bookVO.setKey(number.longValue());
    return bookVO;
  }

}
