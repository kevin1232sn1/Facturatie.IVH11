package avans.ivh11a1.facturatie.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Tinne on 15-3-2017.
 */
public class EmailAddressValidator implements ConstraintValidator<EmailAddress, String> {
    @Override
    public void initialize(EmailAddress emailAddress) {
    }

    @Override
    public boolean isValid(String emailAddress, ConstraintValidatorContext constraintValidatorContext) {
        if(emailAddress == null) {
            return false;
        }

        if (emailAddress.matches("^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$")) return true;

        else return false;
    }
}
