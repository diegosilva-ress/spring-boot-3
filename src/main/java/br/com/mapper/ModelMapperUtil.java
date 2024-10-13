package br.com.mapper;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;

public class ModelMapperUtil {

  private static final ModelMapper mapper = new ModelMapper();

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
