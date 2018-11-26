package lv.java2.shopping_list.services;

import java.util.List;

public interface AddShoppingListValidator {

    List<Error> validate (AddShoppingListRequest request);
}
