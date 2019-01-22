package lv.java2.shopping_list.sharedrules;

import lv.java2.shopping_list.ShoppingListError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmptyStringRule {

    public Optional<ShoppingListError> execute(String stringToCheck, String errorField) {
        if (stringToCheck == null || stringToCheck.isEmpty() || stringToCheck.matches("\\s+")) {
            ShoppingListError error = new ShoppingListError(errorField, "This field cannot be empty");
            return Optional.of(error);
        }
        return Optional.empty();
    }
}
