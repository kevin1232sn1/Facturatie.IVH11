package avans.ivh11a1.facturatie.domain.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Tinne on 15-3-2017.
 */
@Documented
@Constraint(validatedBy = EmailAddressValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAddress {
    String message() default "This value is not a valid email address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}