package lv.java2.shopping_list.item.services.addition.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.item.services.ItemAddRemoveSharedRequest;

import java.util.List;

public interface ItemAdditionValidator {

    List<ShoppingListError> validate (ItemAddRemoveSharedRequest additionReq);
}
