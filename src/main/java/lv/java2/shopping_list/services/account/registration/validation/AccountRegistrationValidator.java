package lv.java2.shopping_list.services.account.registration.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationRequest;

import java.util.List;

public interface AccountRegistrationValidator {

    List<ShoppingListError> validate(AccountRegistrationRequest request);

//    List<ShoppingListError> validateLogin(String login);
//
//    List<ShoppingListError> validatePassword(String plainTextPassword);

}
