package lv.java2.shopping_list.services.product.removal;


import lv.java2.shopping_list.services.ShoppingListError;

import java.util.List;

public interface RemoveProductValidator {

    List<ShoppingListError> validate(RemoveProductRequest request);

}
