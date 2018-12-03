package lv.java2.shopping_list.services.add.shoppinglist.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionRequest;

import java.util.List;

public interface ShoppingListAdditionValidator {

    List<ShoppingListError> validate(ShoppingListAdditionRequest additionRequest);

}
