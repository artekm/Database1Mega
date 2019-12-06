package artur.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootApplication
public class DatabaseApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        PrepareDatabase();
    }

    private void PrepareDatabase() {
        jdbcTemplate
                .execute("create table person ( id integer not null, firstName varchar(255) not null, lastName varchar(255) not null, pesel varchar(20) not null, primary key(id));");
        jdbcTemplate
                .execute("insert into person  values (1,'John','Doe','75041100954'),(2,'Jane','Doe','76112707706'),(300,'Anna','Frank','14310604232'),(400000,'Anna','Noname','18210102896');");
    }
}

