package lv.java2.shopping_list.services.item.update.price.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.ItemUpdateSharedRequest;

import java.util.List;

public interface UpdateItemPriceValidator {

    List<ShoppingListError> validate (ItemUpdateSharedRequest priceRequest);
}
