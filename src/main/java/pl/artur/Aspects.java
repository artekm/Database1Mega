package pl.artur;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Aspect
@Component
public class Aspects {

    private static final Logger logger = LoggerFactory.getLogger(Aspect.class);

    @Around("execution(* pl.artur.PersonService.get*(..))")
    public Object measureExecTime(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("Performing task " + pjp.getSignature() + " " + Arrays.toString(pjp.getArgs()));
        Instant startTime = Instant.now();

        Object result = pjp.proceed();

        Instant stopTime = Instant.now();
        logger.info("Task executed in " + Duration.between(startTime, stopTime).toMillis() + " ms");
        return result;
    }
}
