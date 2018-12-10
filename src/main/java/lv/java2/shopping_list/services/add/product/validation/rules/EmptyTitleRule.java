package lv.java2.shopping_list.services.add.product.validation.rules;

import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
public class EmptyTitleRule {


    public Optional<ShoppingListError> execute(String title) {
        if (title == null || title.isEmpty() || title.matches("\\s+")) {
            ShoppingListError error = new ShoppingListError("title", "Empty title not allowed!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }
}
