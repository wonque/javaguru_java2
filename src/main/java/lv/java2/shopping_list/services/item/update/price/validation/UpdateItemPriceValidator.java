package lv.java2.shopping_list.services.item.update.price.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.ItemUpdateSharedRequest;

import java.util.List;
import java.util.Optional;

public interface UpdateItemPriceValidator {

    Optional<ShoppingListError> validate (ItemUpdateSharedRequest priceRequest);
}
