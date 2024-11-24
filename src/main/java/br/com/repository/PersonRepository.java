package br.com.repository;

import br.com.model.Person;
import br.com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  @Modifying
  @Query("UPDATE Person p SET p.enabled = false where p.id =:id")
  void disablePerson(@Param("id") Long id);

}
