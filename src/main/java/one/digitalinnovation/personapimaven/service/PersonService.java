package one.digitalinnovation.personapimaven.service;


import lombok.AllArgsConstructor;
import one.digitalinnovation.personapimaven.dto.request.PersonDTO;
import one.digitalinnovation.personapimaven.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapimaven.entity.Person;
import one.digitalinnovation.personapimaven.exeception.PersonNotFoundExeception;
import one.digitalinnovation.personapimaven.mapper.PersonMapper;
import one.digitalinnovation.personapimaven.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired) )
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);


        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID");

    }

    public List<PersonDTO> listAll() {
        List<Person> allPeope = personRepository.findAll();
        return allPeope.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());

    }

    public PersonDTO findByID(Long id) throws PersonNotFoundExeception {
        Person person = verifyIfExists(id);


        return personMapper.toDTO(person);
    }


    public void delete(Long id) throws PersonNotFoundExeception {
        verifyIfExists(id);

        personRepository.deleteById(id);
    }


    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundExeception {

        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Uptade person with ID");
    }


    private Person verifyIfExists(Long id) throws PersonNotFoundExeception {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundExeception(id));
    }


    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}