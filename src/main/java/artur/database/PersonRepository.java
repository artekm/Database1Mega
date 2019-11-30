package artur.database;

import com.google.common.primitives.Ints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
class PersonRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private BeanPropertyRowMapper<Person> mapper = new BeanPropertyRowMapper<>(Person.class);

    List<Person> getPeopleWithID(int... Ids) {
        String allIds = Arrays.stream(Ids)
                              .mapToObj(String::valueOf)
                              .collect(Collectors.joining(","));

        return jdbcTemplate.query("select * from person where id in (" + allIds + ")", mapper);
    }

    List<Person> getPeopleWithID2(int... Ids) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource parameters = new MapSqlParameterSource("ids", Ints.asList(Ids));
        RowMapper<Person> personRowMapper = (rs, row) -> new Person(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"));
        return namedParameterJdbcTemplate.query("select * from person where id in (:ids)", parameters, personRowMapper);
    }

    Map<Character, Long> countChars(String input) {
        return input.chars()
                    .mapToObj(i -> (char) i)
                    .collect(Collectors.groupingBy(chr -> chr, Collectors.counting()));
    }
}
