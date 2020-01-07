package pl.artur;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class Aspects {

    @Around("execution(* pl.artur.PersonService.get*(..))")
    public Object measureExecTime(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Performing task " + pjp.getSignature() + " " + Arrays.toString(pjp.getArgs()));
        Instant startTime = Instant.now();

        Object result = pjp.proceed();

        Instant stopTime = Instant.now();
        log.info("Task executed in " + Duration.between(startTime, stopTime).toMillis() + " ms");
        return result;
        //alalalalalal
    }
}
