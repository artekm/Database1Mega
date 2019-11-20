package artur.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
class MyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private BeanPropertyRowMapper<Person> mapper = new BeanPropertyRowMapper<>(Person.class);

    List<Person> getPeopleWithID(int... Ids) {
        String allIds = Arrays.stream(Ids)
                              .mapToObj(String::valueOf)
                              .collect(Collectors.joining(","));

        return jdbcTemplate.query("select * from person where id in (" + allIds + ")", mapper);
    }
}
