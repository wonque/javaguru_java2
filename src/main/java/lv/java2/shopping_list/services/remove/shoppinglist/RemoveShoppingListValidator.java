package lv.java2.shopping_list.services.remove.shoppinglist;

import lv.java2.shopping_list.services.ShoppingListError;

import java.util.List;

public interface RemoveShoppingListValidator {

    List<ShoppingListError> validate(RemoveShoppingListRequest request);

}
