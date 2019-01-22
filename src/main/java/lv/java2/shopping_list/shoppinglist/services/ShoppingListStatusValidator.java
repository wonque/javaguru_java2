package lv.java2.shopping_list.shoppinglist.services;

import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingListStatus;
import lv.java2.shopping_list.ShoppingListError;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingListStatusValidator {

    public Optional<ShoppingListError> execute(ShoppingList shoppingList) {
        if (shoppingList.getStatus().equals(ShoppingListStatus.ARCHIVED)) {
            ShoppingListError error = new ShoppingListError("status",
                    "Cannot add item to archived list");
            return Optional.of(error);
        }
        return Optional.empty();
    }
}
