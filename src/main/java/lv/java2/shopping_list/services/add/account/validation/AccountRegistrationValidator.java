package lv.java2.shopping_list.services.add.account.validation;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.account.registration.AccountRegistrationRequest;

import java.util.List;

public interface AccountRegistrationValidator {

    List<ShoppingListError> validate(AccountRegistrationRequest request);

}
