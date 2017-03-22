package avans.ivh11a1.facturatie.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Tinne on 15-3-2017.
 */

public class ZipcodeValidator implements ConstraintValidator<Zipcode, String> {
    @Override
    public void initialize(Zipcode zipcode) {

    }

    @Override
    public boolean isValid(String zipcode, ConstraintValidatorContext constraintValidatorContext) {
        if(zipcode == null) {
            return false;
        }

        if (zipcode.matches("^[1-9][0-9]{3}\\s?[a-zA-Z]{2}$")) return true;

        else return false;
    }
}