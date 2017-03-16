package avans.ivh11a1.facturatie.domain.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Tinne on 14-3-2017.
 */

@Documented
@Constraint(validatedBy = HouseNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HouseNumber {
    String message() default "This value is not a valid housenumber";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}