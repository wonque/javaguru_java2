package lv.java2.shopping_list.account.services.registration.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.password.validation.PasswordSpellingValidator;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.account.services.registration.login.LoginSpellingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRegistrationValidatorImpl implements AccountRegistrationValidator {

    @Autowired
    private LoginSpellingValidator loginSpellingValidator;

    @Autowired
    private PasswordSpellingValidator passwordSpellingValidator;

    @Override
    public List<ShoppingListError> validate(AccountRegistrationRequest request) {
        List<ShoppingListError> loginErrors = loginSpellingValidator.validate(request.getLogin());
        if(loginErrors.isEmpty()){
            return passwordSpellingValidator.validate(request.getPassword());
        }
        return loginErrors;
    }

//    @Autowired
//    private LoginRules loginRules;
//
//    @Autowired
//    private EmptyStringRule emptyStringRule;
//
//    @Autowired
//    private PasswordRules passwordRules;

//    @Override
//    public List<ShoppingListError> validate(AccountRegistrationRequest request) {
//        List<ShoppingListError> errors = new ArrayList<>();
//        emptyLoginAndPasswordCheck(request).stream().findFirst().ifPresent(errors::add);
//        loginRules.longerThat100symbols(request.getLogin()).ifPresent(errors::add);
//        loginRules.containsAtSignAndDotSign(request.getLogin()).ifPresent(errors::add);
//        loginRules.doesNotContainLetters(request.getLogin()).ifPresent(errors::add);
//        loginRules.lessThan5Length(request.getLogin()).ifPresent(errors::add);
//        if (!errors.isEmpty()) {
//            return errors;
//        }
//        loginRules.duplicateLogin(request.getLogin()).ifPresent(errors::add);
//        passwordRules.containsDigits(request.getPassword()).ifPresent(errors::add);
//        passwordRules.lessThan6Length(request.getPassword()).ifPresent(errors::add);
//        return errors;
//    }


//    private List<ShoppingListError> emptyLoginAndPasswordCheck(AccountRegistrationRequest registrationRequest) {
//        List<ShoppingListError> emptyErrors = new ArrayList<>();
//        emptyStringRule.execute(registrationRequest.getLogin(), "login")
//                .ifPresent(emptyErrors::add);
//        emptyStringRule.execute(registrationRequest.getPassword(), "password")
//                .ifPresent(emptyErrors::add);
//        return emptyErrors;
//    }
}
