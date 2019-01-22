package lv.java2.shopping_list.item.services.update.price.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.item.services.ItemUpdateSharedRequest;

import java.util.Optional;

public interface UpdateItemPriceValidator {

    Optional<ShoppingListError> validate (ItemUpdateSharedRequest priceRequest);
}
