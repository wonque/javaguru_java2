package lv.java2.shopping_list.shoppinglist.services.validation.rules;

import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.ShoppingListError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NullAccountRule {

    public Optional<ShoppingListError> validate(Account account) {
        if (account == null || account.getId() == null) {
            ShoppingListError error = new ShoppingListError("account",
                    "Wrong account ID!");
            error.setStatus(HttpStatus.BAD_REQUEST);
            return Optional.of(error);
        }
        return Optional.empty();
    }
}
