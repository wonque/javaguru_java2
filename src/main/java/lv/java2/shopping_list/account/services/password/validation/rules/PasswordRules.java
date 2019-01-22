package lv.java2.shopping_list.account.services.password.validation.rules;

import lv.java2.shopping_list.ShoppingListError;

import java.util.Optional;

public abstract class PasswordRules {

    protected ShoppingListError createError(String description) {
        return new ShoppingListError("password", description);
    }

    abstract Optional<ShoppingListError> execute (String userEnteredPassword);

}
