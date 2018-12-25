package lv.java2.shopping_list.services.account.registration.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.account.registration.validation.rules.LoginRules;
import lv.java2.shopping_list.services.account.registration.validation.rules.PasswordRules;
import lv.java2.shopping_list.services.sharedrules.EmptyTitleSharedRule;
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
        passwordRules.containsDigits(request.getPlainTextPassword()).ifPresent(errors::add);
        passwordRules.lessThan6Length(request.getPlainTextPassword()).ifPresent(errors::add);
////        passwordRules.matchPasswords(request.getMainPassword(), request.getPasswordToMatch()).ifPresent(errors::add);
        return errors;
    }

//    @Override
//    public List<ShoppingListError> validateLogin(String login) {
//        List<ShoppingListError> loginErrors = new ArrayList<>();
//        emptyTitleSharedRule.execute(login, "login").ifPresent(loginErrors::add);
//        loginRules.containsAtSignAndDotSign(login).ifPresent(loginErrors::add);
//        loginRules.doesNotContainLetters(login).ifPresent(loginErrors::add);
//        loginRules.lessThan5Length(login).ifPresent(loginErrors::add);
//        loginRules.duplicateLogin(login).ifPresent(loginErrors::add);
//        return loginErrors;
//    }
//
//    @Override
//    public List<ShoppingListError> validatePassword(String plainTextPassword) {
//        List<ShoppingListError> passwordErrors = new ArrayList<>();
//        passwordRules.containsDigits(plainTextPassword).ifPresent(passwordErrors::add);
//        passwordRules.lessThan6Length(plainTextPassword).ifPresent(passwordErrors::add);
//        return passwordErrors;
//    }
}
