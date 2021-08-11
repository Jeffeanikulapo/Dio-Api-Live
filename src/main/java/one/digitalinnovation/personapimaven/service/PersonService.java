package one.digitalinnovation.personapimaven.service;


import one.digitalinnovation.personapimaven.dto.request.PersonDTO;
import one.digitalinnovation.personapimaven.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapimaven.entity.Person;
import one.digitalinnovation.personapimaven.mapper.PersonMapper;
import one.digitalinnovation.personapimaven.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public  PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;

    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);


        Person savedPerson = personRepository.save(personToSave);
        return  MessageResponseDTO
                .builder()
                .message("Created person with ID" + savedPerson.getId())
                .build();

    }

    public List<PersonDTO> listAll(){
        List<Person> allPeope = personRepository.findAll();
        return allPeope.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());

    }
}
