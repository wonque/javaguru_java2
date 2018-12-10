package lv.java2.shopping_list.services.add.item.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.item.ItemAdditionRequest;

import java.util.List;

public interface ItemAdditionValidator {

    List<ShoppingListError> validate (ItemAdditionRequest request);
}
