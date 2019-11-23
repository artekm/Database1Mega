package artur.database;

import com.google.common.primitives.Ints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private BeanPropertyRowMapper<Person> mapper = new BeanPropertyRowMapper<>(Person.class);

    List<Person> getPeopleWithID(int... Ids) {
        String allIds = Arrays.stream(Ids)
                              .mapToObj(String::valueOf)
                              .collect(Collectors.joining(","));

        return jdbcTemplate.query("select * from person where id in (" + allIds + ")", mapper);
    }

    List<Person> getPeopleWithID2(int... Ids) {
        SqlParameterSource parameters = new MapSqlParameterSource().addValue("ids", Ints.asList(Ids));
        return namedParameterJdbcTemplate.query("select * from person where id in (:ids)", parameters, mapper);
    }

    List<String> filter(int... ids) {
        if (ids.length < 20)
            throw new IllegalArgumentException("Ids count must be at least 20");
        return Arrays.stream(ids)
                     .skip(ids.length / 2 - 10)
                     .limit(20)
                     .mapToObj(String::valueOf)
                     .collect(Collectors.toList());
    }

    List<String> filter2(int... ids) {
        if (ids.length < 20)
            throw new IllegalArgumentException("Ids count must be at least 20");
        int middle = ids.length / 2;
        return IntStream.range(middle - 10, middle + 10)
                        .mapToObj(i -> String.valueOf(ids[i]))
                        .collect(Collectors.toList());
    }

    Map<Character, Long> countChars(String input) {
        return input.chars()
                    .mapToObj(i -> (char) i)
                    .collect(Collectors.groupingBy(chr -> chr, Collectors.counting()));
    }
}
