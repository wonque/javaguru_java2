package lv.java2.shopping_list.services.add.account.validation.login;


import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.account.validation.rules.LoginRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginValidator {

    @Autowired
    private LoginRules loginRules;

    public LoginValidator(LoginRules loginRules) {
        this.loginRules = loginRules;
    }

    public LoginValidationResponse validateLogin(String login) {
        List<ShoppingListError> loginErrors = new ArrayList<>();
        loginRules.emptyLogin(login).ifPresent(loginErrors::add);
        loginRules.lessThan5Length(login).ifPresent(loginErrors::add);
        loginRules.doesNotContainLetters(login).ifPresent(loginErrors::add);
        loginRules.containsAtSignAndDotSign(login).ifPresent(loginErrors::add);
        loginRules.duplicateLogin(login).ifPresent(loginErrors::add);
        if (loginErrors.isEmpty()) {
            return new LoginValidationResponse(login);
        }
        return new LoginValidationResponse(loginErrors);
    }

    public void displayErrors(List<ShoppingListError> errors) {
        errors.forEach(error -> System.out.println(error.getErrorDescription()));
    }

}
