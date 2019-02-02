package lv.java2.shopping_list.account.services.registration.validation.login;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.registration.validation.login.rules.*;
import lv.java2.shopping_list.sharedrules.EmptyStringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginSpellingValidatorImpl implements LoginSpellingValidator {

    @Autowired
    private ContainsAtSignAndDotSignRule containsAtSignAndDotSignRule;
    @Autowired
    private ContainsLettersRule containsLettersRule;
    @Autowired
    private LessThan5SymbolsLengthRule lessThan5SymbolsLengthRule;
    @Autowired
    private EmptyStringRule emptyStringRule;

    @Override
    public List<ShoppingListError> validate(String userEnteredLogin) {
        List<ShoppingListError> errors = new ArrayList<>();
        emptyStringRule.execute(userEnteredLogin, "login").ifPresent(errors::add);
        containsAtSignAndDotSignRule.apply(userEnteredLogin).ifPresent(errors::add);
        containsLettersRule.apply(userEnteredLogin).ifPresent(errors::add);
        lessThan5SymbolsLengthRule.apply(userEnteredLogin).ifPresent(errors::add);
        return errors;
    }
}
