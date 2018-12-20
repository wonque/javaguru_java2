package lv.java2.shopping_list.services.shoppinglist.addition.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListSharedRequest;

import java.util.List;

public interface ShoppingListAdditionValidator {

    List<ShoppingListError> validate(ShoppingListSharedRequest additionRequest);

}
