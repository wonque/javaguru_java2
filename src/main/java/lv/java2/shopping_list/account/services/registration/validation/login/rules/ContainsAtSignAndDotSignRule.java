package lv.java2.shopping_list.account.services.registration.validation.login.rules;

import lv.java2.shopping_list.ShoppingListError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContainsAtSignAndDotSignRule extends LoginRules {

    public Optional<ShoppingListError> apply(String login) {
        if (!login.contains("@") && !login.contains(".") || login.contains("@") && !login.contains(".")
                || !login.contains("@") && login.contains(".")) {
            return Optional.of(createError("Login is not an email address!"));
        } else {
            return Optional.empty();
        }
    }

}
