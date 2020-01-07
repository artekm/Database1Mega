package pl.artur;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class DatabaseApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DatabaseApplication.class).bannerMode(Banner.Mode.OFF).run(args);
//        SpringApplication.run(DatabaseApplication.class, args);
        //alalalalalalaa
    }
}

