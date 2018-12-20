package lv.java2.shopping_list.services.account.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationRequest;

import java.util.List;

public interface AccountRegistrationValidator {

    List<ShoppingListError> validate(AccountRegistrationRequest request);

    boolean validateLogin(String login);

    boolean validatePassword(String mainPass);

}
