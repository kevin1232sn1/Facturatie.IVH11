package avans.ivh11a1.facturatie.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Tinne on 14-3-2017.
 */

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public void initialize(PhoneNumber phoneNumber) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        if(phoneNumber == null) {
            return false;
        }

        if (phoneNumber.matches("^(?:0|(?:\\+|00) ?31 ?)(?:(?:[1-9] ?(?:[0-9] ?){8})|(?:6 ?-? ?[1-9] ?(?:[0-9] ?){7})|(?:[1,2,3,4,5,7,8,9]\\d ?-? ?[1-9] ?(?:[0-9] ?){6})|(?:[1,2,3,4,5,7,8,9]\\d{2} ?-? ?[1-9] ?(?:[0-9] ?){5}))$")) return true;

        else return false;
    }
}
