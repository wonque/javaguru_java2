package lv.java2.shopping_list.services.add.product.validation.rules;

import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FirstCharacterRule {


    public Optional<ShoppingListError> execute(String title) {
        if (title.substring(0, 1).matches("\\W")) {
            ShoppingListError error = new ShoppingListError("title", "First character in title must be a word-type character");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }

}


