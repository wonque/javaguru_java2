package lv.java2.shopping_list.account.services.registration.login.rules;

import lv.java2.shopping_list.ShoppingListError;


import java.util.Optional;


public abstract class LoginRules {


    protected ShoppingListError createError(String description) {
        return new ShoppingListError("login", description);
    }

    protected abstract Optional<ShoppingListError> execute(String userEnteredLogin);

}
