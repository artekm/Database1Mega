package pl.artur;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    void getPeopleWithID_returnEmptyList_forNoId() {
        List<Person> testPeople = Collections.emptyList();
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(testPeople);

        assertTrue(repository.getPeopleWithID().containsAll(testPeople));
    }

    @Test
    void getPeopleWithID_returnCorrectValues_forOneId() {
        Person johnDoe = new Person(1, "John", "Doe","75041100954");
        List<Person> testPeople = Collections.singletonList(johnDoe);
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(testPeople);

        assertThat(repository.getPeopleWithID(1)).containsExactly(johnDoe);
    }

    @Test
    void getPeopleWithID_returnCorrectValues_forManyId() {
        Person johnDoe = new Person(1, "John", "Doe","75041100954");
        Person janneDoe = new Person(2, "Janne", "Doe","76112707706");
        Person johnBlack = new Person(3, "John", "Black","18210102896");
        List<Person> testPeople = Arrays.asList(johnDoe, janneDoe, johnBlack);
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(testPeople);

        assertTrue(repository.getPeopleWithID(1, 2, 3).containsAll(testPeople));
    }
}