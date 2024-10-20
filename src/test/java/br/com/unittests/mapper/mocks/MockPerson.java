package br.com.unittests.mapper.mocks;

import br.com.data.vo.PersonVO;
import br.com.model.Person;
import java.util.ArrayList;
import java.util.List;

public class MockPerson {

  public Person mockEntity() {
    return mockEntity(0);
  }

  public PersonVO mockVO() {
    return mockVO(0);
  }

  public List<Person> mockEntityList() {
    List<Person> persons = new ArrayList<Person>();
    for (int i = 0; i < 14; i++) {
      persons.add(mockEntity(i));
    }
    return persons;
  }

  public List<PersonVO> mockVOList() {
    List<PersonVO> persons = new ArrayList<PersonVO>();
    for (int i = 0; i < 14; i++) {
      persons.add(mockVO(i));
    }
    return persons;
  }

  public Person mockEntity(Integer number) {
    Person person = new Person();
    person.setAddress("Address Test" + number);
    person.setFirstName("First Name Test" + number);
    person.setLastName("Last Name Test" + number);
    person.setGender(((number % 2) == 0) ? "Male" : "Female");
    person.setId(number.longValue());
    return person;
  }

  public PersonVO mockVO(Integer number) {
    PersonVO personVO = new PersonVO();
    personVO.setAddress("Address Test" + number);
    personVO.setFirstName("First Name Test" + number);
    personVO.setLastName("Last Name Test" + number);
    personVO.setGender(((number % 2) == 0) ? "Male" : "Female");
    personVO.setId(number.longValue());
    return personVO;
  }

}
