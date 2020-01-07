package pl.artur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Value("${myDate}")
    private LocalDate myDate;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @Value("${myTime:#{T(java.time.LocalTime).now()}}")
    private LocalTime myTime;

    public People getThemAll() {
//        return personRepository.getThemAll();
        int[] Ids = new int[1_000_000];
        Arrays.setAll(Ids, x -> x);
//        return personRepository.getPeopleWithID(Ids);
        return personRepository.getPeopleWithID2(Ids);
    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void doSth() {
        System.out.println(myDate);
        System.out.println(myTime);
        System.out.println("Timer");
    }

    public Person getById(Integer id) {
        return personRepository.getById(id);
    }

    public Person getByPesel(String pesel) {
        return personRepository.getByPesel(pesel);
    }
}
