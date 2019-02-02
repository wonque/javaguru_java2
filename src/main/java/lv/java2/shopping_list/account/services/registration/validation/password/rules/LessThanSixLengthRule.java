package lv.java2.shopping_list.account.services.registration.validation.password.rules;

import lv.java2.shopping_list.ShoppingListError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LessThanSixLengthRule extends PasswordRules {

    public Optional<ShoppingListError> apply(String password) {
        if (password.length() < 6) {
            return Optional.of(createError(
                    "Password too short! Minimum length is 6 symbols"));
        } else {
            return Optional.empty();
        }
    }
}
