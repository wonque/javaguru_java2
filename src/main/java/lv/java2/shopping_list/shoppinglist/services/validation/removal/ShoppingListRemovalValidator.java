package lv.java2.shopping_list.shoppinglist.services.validation.removal;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListSharedRequest;

import java.util.List;

public interface ShoppingListRemovalValidator {

    List<ShoppingListError> validate(ShoppingListSharedRequest removalRequest);
}
