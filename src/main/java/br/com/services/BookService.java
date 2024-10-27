package br.com.services;

import br.com.data.vo.BookVO;
import java.util.List;

public interface BookService {

  BookVO findById(Long id);

  List<BookVO> findAll();

  BookVO create(BookVO bookVO);

  BookVO update(BookVO bookVO);

  void delete(Long id);

}
