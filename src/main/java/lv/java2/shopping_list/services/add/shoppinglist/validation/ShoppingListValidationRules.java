package lv.java2.shopping_list.services.add.shoppinglist.validation;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.services.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingListValidationRules {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public Optional<Error> emptyTitleRule(String title) {
        if (title == null || title.isEmpty() || title.matches("\\s+")) {
            Error error = new Error("title", "Empty title not allowed!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Error> duplicateEntryRule(String title) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.getByTitle(title);
        if(shoppingList.isPresent()){
            Error error = new Error("title",
                    "Shoppinglist with title " + title + " already in database!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }
}
