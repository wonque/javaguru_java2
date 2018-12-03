package lv.java2.shopping_list.services.add.shoppinglist.validation;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingListValidationRules {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public Optional<ShoppingListError> emptyTitleRule(String title) {
        if (title == null || title.isEmpty() || title.matches("\\s+")) {
            ShoppingListError error = new ShoppingListError("title", "Empty title not allowed!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }

    public Optional<ShoppingListError> duplicateEntryRule(String title) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.getByTitle(title);
        if(shoppingList.isPresent()){
            ShoppingListError error = new ShoppingListError("title",
                    "Shopping list with title " + title + " already in database!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }
}
