package one.digitalinnovation.personapimaven.repository;


import one.digitalinnovation.personapimaven.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository <Person, Long> {



}
