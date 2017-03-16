package avans.ivh11a1.facturatie.domain.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by Tinne on 14-3-2017.
 */

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "This value is not a valid (cell)phonenumber";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
