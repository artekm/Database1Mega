package pl.artur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Validated
@RestController
public class Controller {

    @Autowired
    PersonService personService;

    @GetMapping(value = "/persons", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public People getAll() { return personService.getThemAll(); }

    @GetMapping(value = "/person/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Person getById(@PathVariable String id) {
        return personService.getById(Integer.valueOf(id));
    }

    @GetMapping(value = "/person", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Person getByPesel(@RequestParam @Pesel String pesel) {
        return personService.getByPesel(pesel);
    }
}
