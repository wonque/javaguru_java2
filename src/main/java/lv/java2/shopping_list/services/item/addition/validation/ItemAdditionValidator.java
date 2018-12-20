package lv.java2.shopping_list.services.item.addition.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.ItemAddRemoveSharedRequest;

import java.util.List;

public interface ItemAdditionValidator {

    List<ShoppingListError> validate (ItemAddRemoveSharedRequest additionReq);
}
