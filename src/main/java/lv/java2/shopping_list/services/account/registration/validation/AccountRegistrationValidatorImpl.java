package lv.java2.shopping_list.services.account.registration.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.account.registration.validation.rules.LoginRules;
import lv.java2.shopping_list.services.account.registration.validation.rules.PasswordRules;
import lv.java2.shopping_list.services.sharedrules.EmptyTitleSharedRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
        emptyLoginAndPasswordCheck(request).stream().findFirst().ifPresent(errors::add);
        if (!errors.isEmpty()) {
            return errors;
        }
        loginRules.longerThat100symbols(request.getLogin()).ifPresent(errors::add);
        loginRules.containsAtSignAndDotSign(request.getLogin()).ifPresent(errors::add);
        loginRules.doesNotContainLetters(request.getLogin()).ifPresent(errors::add);
        loginRules.lessThan5Length(request.getLogin()).ifPresent(errors::add);
        loginRules.duplicateLogin(request.getLogin()).ifPresent(errors::add);
        passwordRules.containsDigits(request.getPassword()).ifPresent(errors::add);
        passwordRules.lessThan6Length(request.getPassword()).ifPresent(errors::add);
        return errors;
    }


    private List<ShoppingListError> emptyLoginAndPasswordCheck(AccountRegistrationRequest registrationRequest) {
        List<ShoppingListError> emptyErrors = new ArrayList<>();
        emptyTitleSharedRule.execute(registrationRequest.getLogin(), "login")
                .ifPresent(emptyErrors::add);
        emptyTitleSharedRule.execute(registrationRequest.getPassword(), "password")
                .ifPresent(emptyErrors::add);
        return emptyErrors;
    }
}
