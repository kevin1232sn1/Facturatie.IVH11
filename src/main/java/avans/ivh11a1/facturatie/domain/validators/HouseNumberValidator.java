package avans.ivh11a1.facturatie.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Tinne on 14-3-2017.
 */

public class HouseNumberValidator implements ConstraintValidator<HouseNumber, String> {
    @Override
    public void initialize(HouseNumber houseNumber) {
    }

    @Override
    public boolean isValid(String houseNumber, ConstraintValidatorContext constraintValidatorContext) {
        if(houseNumber == null) {
            return false;
        }

        if (houseNumber.matches("^[0-9]+([A-Za-z]\\b)?")) return true;

        else return false;
    }
}
