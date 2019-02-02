package lv.java2.shopping_list.shoppinglist.services.addition.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListSharedRequest;

import java.util.List;

public interface ShoppingListAdditionValidator {

    List<ShoppingListError> validate(ShoppingListSharedRequest additionRequest);

}
