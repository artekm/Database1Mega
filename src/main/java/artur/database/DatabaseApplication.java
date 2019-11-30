package artur.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootApplication
public class DatabaseApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        PrepareDatabase();

        int[] table = new int[1_000_000];
        Arrays.setAll(table, i -> i);
        List<Person> result = repository.getPeopleWithID(table);
        log.info(result.toString());
        List<Person> result2 = repository.getPeopleWithID2(table);
        log.info(result2.toString());

        Filters filters = new Filters();
        log.info(filters.filterVersion1(table).toString());
        log.info(filters.filterVersion2(table).toString());

        String testString = "abeeecaaddd";
        log.info(repository.countChars(testString).toString());

        Integer[] arrayObjects = new Integer[]{1,2,3,4,5};
        System.out.println(Arrays.asList(arrayObjects)); //[1, 2, 3, 4, 5] OK

        int[] arrayPrimitives = new int[]{1,2,3,4,5};
        System.out.println(Arrays.asList(arrayPrimitives)); //[I@5d7ca698] it seems to be a list of just one element and this element is an array of ints
    }

    private void PrepareDatabase() {
        jdbcTemplate
                .execute("create table person ( id integer not null, firstName varchar(255) not null, lastName varchar(255) not null, primary key(id));");
        jdbcTemplate
                .execute("insert into person  values (1,'John','Doe'),(2,'Jane','Doe'),(300,'Anna','Frank'),(400000,'Anna','Noname');");
    }
}

