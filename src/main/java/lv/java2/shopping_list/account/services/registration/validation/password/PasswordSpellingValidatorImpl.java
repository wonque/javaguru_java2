package lv.java2.shopping_list.account.services.registration.validation.password;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.registration.validation.password.rules.ContainsThreeDigitsRule;
import lv.java2.shopping_list.account.services.registration.validation.password.rules.LessThanSixLengthRule;
import lv.java2.shopping_list.sharedrules.EmptyStringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordSpellingValidatorImpl implements PasswordSpellingValidator {

    @Autowired
    private LessThanSixLengthRule lessThanSixLengthRule;
    @Autowired
    private ContainsThreeDigitsRule containsThreeDigitsRule;
    @Autowired
    private EmptyStringRule emptyStringRule;

    @Override
    public List<ShoppingListError> validate(String plaintTextPassword) {
        List<ShoppingListError> errors = new ArrayList<>();
        emptyStringRule.execute(plaintTextPassword, "password").ifPresent(errors::add);
        if (errors.isEmpty()) {
            lessThanSixLengthRule.apply(plaintTextPassword).ifPresent(errors::add);
            containsThreeDigitsRule.apply(plaintTextPassword).ifPresent(errors::add);
        }
        return errors;
    }
}