package br.com.mapper;

import br.com.data.vo.BookVO;
import br.com.data.vo.PersonVO;
import br.com.model.Book;
import br.com.model.Person;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;

public class ModelMapperUtil {

  private static final ModelMapper mapper = new ModelMapper();

  static {
    mapper.createTypeMap(Person.class, PersonVO.class).addMapping(Person::getId, PersonVO::setKey);
    mapper.createTypeMap(PersonVO.class, Person.class).addMapping(PersonVO::getKey, Person::setId);

    mapper.createTypeMap(Book.class, BookVO.class).addMapping(Book::getId, BookVO::setKey);
    mapper.createTypeMap(BookVO.class, Book.class).addMapping(BookVO::getKey, Book::setId);
  }

  public static <O, D> D parseObject(O origin, Class<D> destination) {
    return mapper.map(origin, destination);
  }

  public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
    List<D> destinationObjects = new ArrayList<D>();
    for (O object : origin) {
      destinationObjects.add(mapper.map(object, destination));
    }
    return destinationObjects;
  }

}
