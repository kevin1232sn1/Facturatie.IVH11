package avans.ivh11a1.facturatie.domain.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Tinne on 15-3-2017.
 */
@Documented
@Constraint(validatedBy = ZipcodeValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Zipcode {
    String message() default "{facturatie.customvalidation.Zipcode.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
