package br.com.services.impl;

import br.com.controllers.BookController;
import br.com.data.vo.BookVO;
import br.com.exceptions.RequiredObjectIsNullException;
import br.com.exceptions.ResourceNotFoundException;
import br.com.mapper.ModelMapperUtil;
import br.com.model.Book;
import br.com.repository.BookRepository;
import br.com.services.BookService;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  private final Logger logger = Logger.getLogger(BookServiceImpl.class.getName());

  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public BookVO findById(Long id) {
    logger.info("Finding one book");
    var entity = bookRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

    var bookVO = ModelMapperUtil.parseObject(entity, BookVO.class);
    bookVO.add(
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).findById(id))
            .withSelfRel());

    return bookVO;
  }

  @Override
  public List<BookVO> findAll() {
    logger.info("Finding all books");
    var books = ModelMapperUtil.parseListObjects(bookRepository.findAll(), BookVO.class);

    books.forEach(p -> p.add(WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(BookController.class).findById(p.getKey())).withSelfRel()));

    return books;
  }

  @Override
  public BookVO create(BookVO bookVO) {
    if(bookVO == null) {
      throw new RequiredObjectIsNullException();
    }
    logger.info("Creating a new book");
    var entity = ModelMapperUtil.parseObject(bookVO, Book.class);
    var vo = ModelMapperUtil.parseObject(bookRepository.save(entity), BookVO.class);

    vo.add(WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(BookController.class).findById(vo.getKey())).withSelfRel());

    return vo;
  }

  @Override
  public BookVO update(BookVO bookVO) {
    if(bookVO == null) {
      throw new RequiredObjectIsNullException();
    }
    logger.info("Updating a new book");
    var entity = bookRepository.findById(bookVO.getKey())
        .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

    entity.setAuthor(bookVO.getAuthor());
    entity.setLaunchDate(bookVO.getLaunchDate());
    entity.setPrice(bookVO.getPrice());
    entity.setTitle(bookVO.getTitle());

    var vo = ModelMapperUtil.parseObject(bookRepository.save(entity), BookVO.class);

    vo.add(WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(BookController.class).findById(vo.getKey())).withSelfRel());

    return vo;
  }

  @Override
  public void delete(Long id) {
    logger.info("Deleting a book");

    var entity = bookRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

    bookRepository.delete(entity);

  }
}
