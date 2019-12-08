package pl.artur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getThemAll() {
        return personRepository.getThemAll();
    }

    public Person getById(Integer id) {
        return personRepository.getById(id);
    }

    public Person getByPesel(String pesel){
        return personRepository.getByPesel(pesel);
    }
}
