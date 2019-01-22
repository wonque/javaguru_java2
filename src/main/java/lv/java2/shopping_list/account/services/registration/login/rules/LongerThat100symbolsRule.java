package lv.java2.shopping_list.account.services.registration.login.rules;

import lv.java2.shopping_list.ShoppingListError;

import java.util.Optional;

public class LongerThat100symbolsRule extends LoginRules {

    public Optional<ShoppingListError> execute (String login) {
        if (login != null && login.length() > 100) {
            return Optional.of(createError("Login is too long - maximum 100 symbols"));
        }
        return Optional.empty();
    }
}
