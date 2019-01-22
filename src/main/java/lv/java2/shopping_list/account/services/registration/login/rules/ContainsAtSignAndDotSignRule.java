package lv.java2.shopping_list.account.services.registration.login.rules;

import lv.java2.shopping_list.ShoppingListError;

import java.util.Optional;

public class ContainsAtSignAndDotSignRule extends LoginRules {

    public Optional<ShoppingListError> execute (String login) {
        if (!login.contains("@") && !login.contains(".") || login.contains("@") && !login.contains(".")
                || !login.contains("@") && login.contains(".")) {
            return Optional.of(createError("Login is not an email address!"));
        } else {
            return Optional.empty();
        }
    }
}
