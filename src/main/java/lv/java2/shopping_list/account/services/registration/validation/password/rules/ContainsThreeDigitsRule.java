package lv.java2.shopping_list.account.services.registration.validation.password.rules;

import lv.java2.shopping_list.ShoppingListError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContainsThreeDigitsRule extends PasswordRules {

    public Optional<ShoppingListError> apply(String password) {
        int digits = 0;
        for (char character : password.toCharArray()) {
            if (Character.isDigit(character)) {
                digits++;
            }
            if (digits == 3) {
                return Optional.empty();
            }
        }
        return Optional.of(createError(
                "Password must contain at least three digits"));
    }
}
