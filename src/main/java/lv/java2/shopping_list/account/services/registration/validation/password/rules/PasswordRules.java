package lv.java2.shopping_list.account.services.registration.validation.password.rules;

import lv.java2.shopping_list.ShoppingListError;

import java.util.Optional;

public abstract class PasswordRules {

    protected ShoppingListError createError(String description) {
        ShoppingListError error = new ShoppingListError("password", description);
        error.setErrorDatetimeToNow();
        return error;
    }

    abstract Optional<ShoppingListError> apply(String userEnteredPassword);

}
