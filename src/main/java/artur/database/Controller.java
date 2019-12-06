package artur.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    PersonService personService;

    @GetMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getAllPerson() {
        return personService.getThemAll();
    }

    @GetMapping(value = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getSinglePerson(@PathVariable Integer id) {
        return personService.getSinglePerson(id);
    }
}
