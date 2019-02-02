package lv.java2.shopping_list.account.services.registration.validation.password;

import lv.java2.shopping_list.ShoppingListError;

import java.util.List;

public interface PasswordSpellingValidator {

    List<ShoppingListError> validate(String plaintTextPassword);
}

