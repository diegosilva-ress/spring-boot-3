package br.com.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import org.springframework.hateoas.RepresentationModel;

@JsonPropertyOrder({"key", "firstName", "lastName", "address", "gender"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

  @Serial
  private static final long serialVersionUID = -3201786289465992310L;

  @JsonProperty("id")
  private Long key;
  private String firstName;
  private String lastName;
  private String address;
  private String gender;

  public Long getKey() {
    return key;
  }

  public void setKey(Long key) {
    this.key = key;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    PersonVO person = (PersonVO) o;
    return Objects.equals(key, person.key) && Objects.equals(firstName, person.firstName)
        && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address)
        && Objects.equals(gender, person.gender);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, firstName, lastName, address, gender);
  }
}
