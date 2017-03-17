package avans.ivh11a1.facturatie.domain.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Tinne on 15-3-2017.
 */
@Documented
@Constraint(validatedBy = EmailAddressValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAddress {
    String message() default "{facturatie.customvalidation.EmailAddress.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}