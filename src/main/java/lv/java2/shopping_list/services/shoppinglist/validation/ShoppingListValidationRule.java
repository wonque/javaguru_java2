package lv.java2.shopping_list.services.shoppinglist.validation;

import lv.java2.shopping_list.web.dto.ShoppingListDTO;

public interface ShoppingListValidationRule {

    void validate(ShoppingListDTO dto);

}
