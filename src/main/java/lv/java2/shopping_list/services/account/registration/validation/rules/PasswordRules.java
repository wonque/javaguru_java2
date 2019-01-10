package lv.java2.shopping_list.services.account.registration.validation.rules;

import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PasswordRules {

    public Optional<ShoppingListError> lessThan6Length(String password) {
        if (password.length() < 6) {
            return Optional.of(createError("Password too short! Minimum length is 6 symbols"));
        } else {
            return Optional.empty();
        }
    }

    public Optional<ShoppingListError> containsDigits(String password) {
        int digits = 0;
        for (char character : password.toCharArray()) {
            if (Character.isDigit(character)) {
                digits++;
            }
            if (digits == 3) {
                return Optional.empty();
            }
        }
        return Optional.of(createError("Password must contain at least three digits"));
    }

    private ShoppingListError createError(String description) {
        return new ShoppingListError("password", description);
    }


}
