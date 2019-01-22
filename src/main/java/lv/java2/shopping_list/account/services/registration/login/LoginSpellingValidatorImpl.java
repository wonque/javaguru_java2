package lv.java2.shopping_list.account.services.registration.login;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.registration.login.rules.*;
import lv.java2.shopping_list.sharedrules.EmptyStringRule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LoginSpellingValidatorImpl implements LoginSpellingValidator {

    @Autowired
    private ContainsAtSignAndDotSignRule containsAtSignAndDotSignRule;
    @Autowired
    private DoesNotContainLettersRule doesNotContainLettersRule;
    @Autowired
    private DuplicateLoginRule duplicateLoginRule;
    @Autowired
    private LessThan5SymbolsLengthRule lessThan5SymbolsLengthRule;
    @Autowired
    private LongerThat100symbolsRule longerThat100symbolsRule;
    @Autowired
    private EmptyStringRule emptyStringRule;

    @Override
    public List<ShoppingListError> validate(String userEnteredLogin) {
        List<ShoppingListError> errors = new ArrayList<>();
        emptyStringRule.execute(userEnteredLogin, "login").ifPresent(errors::add);
        containsAtSignAndDotSignRule.execute(userEnteredLogin).ifPresent(errors::add);
        doesNotContainLettersRule.execute(userEnteredLogin).ifPresent(errors::add);
        lessThan5SymbolsLengthRule.execute(userEnteredLogin).ifPresent(errors::add);
        longerThat100symbolsRule.execute(userEnteredLogin).ifPresent(errors::add);
        if (errors.isEmpty()) {
            duplicateLoginRule.execute(userEnteredLogin).ifPresent(errors::add);
        }
        return errors;
    }
}
