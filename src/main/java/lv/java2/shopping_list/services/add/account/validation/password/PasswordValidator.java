package lv.java2.shopping_list.services.add.account.validation.password;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.account.validation.rules.PasswordRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PasswordValidator {

    @Autowired
    private PasswordRules passwordRules;

    public PasswordValidator(PasswordRules passwordRules) {
        this.passwordRules = passwordRules;
    }

    public PasswordValidationResponse validatePassword(PasswordValidationRequest request) {
        List<ShoppingListError> passErrors = new ArrayList<>();
        passwordRules.lessThan6Length(request.getEnteredPassword()).ifPresent(passErrors::add);
        passwordRules.containsDigits(request.getEnteredPassword()).ifPresent(passErrors::add);
        passwordRules.matchPasswords(request.getEnteredPassword(), request.getConfirmedPassword()).ifPresent(passErrors::add);
        if (passErrors.isEmpty()) {
            return new PasswordValidationResponse(request.getEnteredPassword());
        } else {
            return new PasswordValidationResponse(passErrors);
        }
    }
}
