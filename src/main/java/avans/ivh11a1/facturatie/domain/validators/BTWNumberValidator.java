package avans.ivh11a1.facturatie.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Tinne on 15-3-2017.
 */

public class BTWNumberValidator implements ConstraintValidator<BTWNumber, String> {
    @Override
    public void initialize(BTWNumber btwNumber) {
    }

    @Override
    public boolean isValid(String btwNumber, ConstraintValidatorContext constraintValidatorContext) {
        if(btwNumber == null) {
            return false;
        }

        if (btwNumber.matches("(NL)?[0-9]{9}B[0-9]{2}")) return true;

        else return false;
    }
}