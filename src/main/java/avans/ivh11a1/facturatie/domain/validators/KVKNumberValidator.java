package avans.ivh11a1.facturatie.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Tinne on 14-3-2017.
 */
public class KVKNumberValidator implements ConstraintValidator<KVKNumber, Integer> {
    @Override
    public void initialize(KVKNumber kvkNumber) {
    }

    @Override
    public boolean isValid(Integer i, ConstraintValidatorContext constraintValidatorContext) {
        String kvkNumber;

        if(i == null || i == 0) {
            return false;
        }

        try {
            kvkNumber = Integer.toString(i);
        }
        catch (Exception e) {
            return false;
        }

        if (kvkNumber.matches("^\\d{8}$")) return true;

        else return false;
    }
}
