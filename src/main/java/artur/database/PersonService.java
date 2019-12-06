package artur.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getThemAll(){
        return personRepository.getThemAll();
    }

    public Person getSinglePerson(Integer id){
        return personRepository.getSinglePerson(id);
    }
}
