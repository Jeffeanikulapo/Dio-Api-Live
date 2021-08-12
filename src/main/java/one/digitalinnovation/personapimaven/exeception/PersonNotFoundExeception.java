package one.digitalinnovation.personapimaven.exeception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundExeception extends Exception {
    public PersonNotFoundExeception(Long id) {
        super("Person not found whit id " + id);

    }
}
