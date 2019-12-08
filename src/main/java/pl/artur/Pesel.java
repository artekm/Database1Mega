package pl.artur;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PeselValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Pesel {
    String message() default "Pesel is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
