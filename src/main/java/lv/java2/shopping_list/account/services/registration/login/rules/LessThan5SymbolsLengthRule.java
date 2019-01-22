package lv.java2.shopping_list.account.services.registration.login.rules;

import lv.java2.shopping_list.ShoppingListError;

import java.util.Optional;

public class LessThan5SymbolsLengthRule extends LoginRules {

    public Optional<ShoppingListError> execute (String login) {
        if (login.length() < 5) {
            return Optional.of(createError("Login too short! Minimum length is 5 symbols"));
        } else {
            return Optional.empty();
        }
    }
}
