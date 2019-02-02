package lv.java2.shopping_list.account.services.registration.validation.login;

import lv.java2.shopping_list.ShoppingListError;

import java.util.List;

public interface LoginSpellingValidator {

    List<ShoppingListError> validate (String userEnteredLogin);

}
