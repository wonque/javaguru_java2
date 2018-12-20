package lv.java2.shopping_list.services.account.validation.rules;

import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PasswordRules {

    public Optional<ShoppingListError> lessThan6Length(String password) {
        if (password.length() < 6) {
            ShoppingListError error = new ShoppingListError("password", "Password too short! Minimum length is 6 symbols");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }

    public Optional<ShoppingListError> containsDigits(String password) {
        if (!password.matches(".*[0-9].*")) {
            ShoppingListError error = new ShoppingListError("password", "Password must contain at least one digit");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }

//    public Optional<ShoppingListError> matchPasswords(String enteredPassword, String confirmationPassword) {
//        if (!enteredPassword.equals(confirmationPassword)) {
//            ShoppingListError error =
//                    new ShoppingListError("password", "Entered password and confirmation password must match!");
//            return Optional.of(error);
//
//        } else {
//            return Optional.empty();
//        }
//    }
}
