package lv.java2.shopping_list.services.add.account.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.add.account.validation.rules.LoginRules;
import lv.java2.shopping_list.services.add.account.validation.rules.PasswordRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRegistrationValidatorImpl implements AccountRegistrationValidator {

    @Autowired
    private LoginRules loginRules;

    @Autowired
    private PasswordRules passwordRules;

    @Override
    public List<ShoppingListError> validate(AccountRegistrationRequest request) {
        List<ShoppingListError> errors = new ArrayList<>();
        loginRules.emptyLogin(request.getLogin()).ifPresent(errors::add);
        loginRules.containsAtSignAndDotSign(request.getLogin()).ifPresent(errors::add);
        loginRules.doesNotContainLetters(request.getLogin()).ifPresent(errors::add);
        loginRules.lessThan5Length(request.getLogin()).ifPresent(errors::add);
        loginRules.duplicateLogin(request.getLogin()).ifPresent(errors::add);
        passwordRules.containsDigits(request.getMainPassword()).ifPresent(errors::add);
        passwordRules.lessThan6Length(request.getMainPassword()).ifPresent(errors::add);
        passwordRules.matchPasswords(request.getMainPassword(), request.getPasswordToMatch()).ifPresent(errors::add);
        return errors;
    }
}
