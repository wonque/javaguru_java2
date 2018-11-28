package lv.java2.shopping_list.services.add.shoppinglist.validation;

import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionRequest;

import java.util.List;

public interface ShoppingListAdditionValidator {

    List<Error> validate(ShoppingListAdditionRequest additionRequest);

}
