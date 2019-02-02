package lv.java2.shopping_list.account.services.registration.validation.login.rules;

import lv.java2.shopping_list.ShoppingListError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LessThan5SymbolsLengthRule extends LoginRules {

    public Optional<ShoppingListError> apply(String login) {
        if (login.length() < 5) {
            return Optional.of(createError("Login too short! Minimum length is 5 symbols"));
        } else {
            return Optional.empty();
        }
    }
}
