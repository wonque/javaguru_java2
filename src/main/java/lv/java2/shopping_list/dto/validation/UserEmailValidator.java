package lv.java2.shopping_list.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserEmailValidator implements ConstraintValidator<UserEmailCostraint, String> {

    @Override
    public void initialize(UserEmailCostraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && email.length() > 0 && email.matches(".+@.+\\..+");
    }
}
