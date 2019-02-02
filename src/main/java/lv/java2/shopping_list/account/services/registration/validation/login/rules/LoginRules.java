package lv.java2.shopping_list.account.services.registration.validation.login.rules;

import lv.java2.shopping_list.ShoppingListError;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public abstract class LoginRules {


    protected ShoppingListError createError(String description) {
        ShoppingListError error = new ShoppingListError("login", description);
        error.setErrorDatetimeToNow();
        return error;
    }

    protected abstract Optional<ShoppingListError> apply(String userEnteredLogin);

}
