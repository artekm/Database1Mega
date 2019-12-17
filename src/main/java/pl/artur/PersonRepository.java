package pl.artur;

import com.google.common.primitives.Ints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
class PersonRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private BeanPropertyRowMapper<Person> mapper = new BeanPropertyRowMapper<>(Person.class);

    @PostConstruct
    private void PrepareDatabase() {
        jdbcTemplate
                .execute("create table person ( id integer not null, firstName varchar(255) not null, lastName varchar(255) not null, pesel varchar(20) not null, primary key(id));");
        jdbcTemplate
                .execute("insert into person  values (1,'John','Doe','75041100954'),(2,'Jane','Doe','76112707706'),(300,'Anna','Frank','14310604232'),(400000,'Anna','Noname','18210102896');");
    }

    public People getThemAll() {
        return new People(jdbcTemplate.query("select * from person", mapper));
    }

    public Person getByPesel(String pesel) {
        List<Person> res = jdbcTemplate.query("select * from person where pesel = ?", mapper, pesel);
        return res.isEmpty() ? null : res.get(0);
    }

    public Person getById(int id) {
        List<Person> res = jdbcTemplate.query("select * from person where id = ?", mapper, id);
        return res.isEmpty() ? null : res.get(0);
    }

    public List<Person> getPeopleWithID(int... Ids) {
        String allIds = Arrays.stream(Ids)
                              .mapToObj(String::valueOf)
                              .collect(Collectors.joining(","));

        return jdbcTemplate.query("select * from person where id in (" + allIds + ")", mapper);
    }

    public List<Person> getPeopleWithID2(int... Ids) {
        SqlParameterSource parameters = new MapSqlParameterSource("ids", Ints.asList(Ids));
        return namedParameterJdbcTemplate.query("select * from person where id in (:ids)", parameters, mapper);
    }
}
