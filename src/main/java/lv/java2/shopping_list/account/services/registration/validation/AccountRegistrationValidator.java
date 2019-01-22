package lv.java2.shopping_list.account.services.registration.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationRequest;

import java.util.List;

public interface AccountRegistrationValidator {

    List<ShoppingListError> validate(AccountRegistrationRequest request);

}
