package lv.java2.shopping_list.services.account.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.account.validation.rules.LoginRules;
import lv.java2.shopping_list.services.account.validation.rules.PasswordRules;
import lv.java2.shopping_list.services.EmptyTitleSharedRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRegistrationValidatorImpl implements AccountRegistrationValidator {

    @Autowired
    private LoginRules loginRules;

    @Autowired
    private EmptyTitleSharedRule emptyTitleSharedRule;

    @Autowired
    private PasswordRules passwordRules;

    @Override
    public List<ShoppingListError> validate(AccountRegistrationRequest request) {
        List<ShoppingListError> errors = new ArrayList<>();
        emptyTitleSharedRule.execute(request.getLogin(), "login").ifPresent(errors::add);
        loginRules.containsAtSignAndDotSign(request.getLogin()).ifPresent(errors::add);
        loginRules.doesNotContainLetters(request.getLogin()).ifPresent(errors::add);
        loginRules.lessThan5Length(request.getLogin()).ifPresent(errors::add);
        loginRules.duplicateLogin(request.getLogin()).ifPresent(errors::add);
        passwordRules.containsDigits(request.getMainPassword()).ifPresent(errors::add);
        passwordRules.lessThan6Length(request.getMainPassword()).ifPresent(errors::add);
//        passwordRules.matchPasswords(request.getMainPassword(), request.getPasswordToMatch()).ifPresent(errors::add);
        return errors;
    }


    //Used in console UI
    @Override
    public boolean validateLogin(String login) {
        List<ShoppingListError> errors = new ArrayList<>();
        emptyTitleSharedRule.execute(login, "login").ifPresent(errors::add);
        loginRules.containsAtSignAndDotSign(login).ifPresent(errors::add);
        loginRules.doesNotContainLetters(login).ifPresent(errors::add);
        loginRules.lessThan5Length(login).ifPresent(errors::add);
        loginRules.duplicateLogin(login).ifPresent(errors::add);
        return errors.isEmpty();

    }

    //Used in console UI
    @Override
    public boolean validatePassword(String mainPass) {
        List<ShoppingListError> errors = new ArrayList<>();
        passwordRules.containsDigits(mainPass).ifPresent(errors::add);
        passwordRules.lessThan6Length(mainPass).ifPresent(errors::add);
//        passwordRules.matchPasswords(mainPass, confirmedPass).ifPresent(errors::add);
        return errors.isEmpty();
    }
}
