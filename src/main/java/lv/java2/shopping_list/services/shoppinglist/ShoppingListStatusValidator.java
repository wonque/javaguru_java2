package lv.java2.shopping_list.services.shoppinglist;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListStatus;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingListStatusValidator {

    public Optional<ShoppingListError> execute(ShoppingList shoppingList) {
        if (shoppingList.getStatus().equals(ShoppingListStatus.ARCHIVED)) {
            ShoppingListError error = new ShoppingListError("status", "Cannot add item to archived list");
            return Optional.of(error);
        }
        return Optional.empty();
    }
}
