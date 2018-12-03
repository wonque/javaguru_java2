package lv.java2.shopping_list.services.remove.shoppinglist;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RemoveShoppingListRules {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public Optional<ShoppingListError> nullTitleRule(String title) {
        if (title == null) {
            ShoppingListError error = new ShoppingListError("title", "Shopping list title cannot be empty!");
            return Optional.of(error);
        }
        return Optional.empty();
    }

    public Optional<ShoppingListError> shoppingListPresenceInDataBaseRule(String title) {
        Optional<ShoppingList> foundedList = shoppingListRepository.getByTitle(title);
        if (!foundedList.isPresent()) {
            ShoppingListError error = new ShoppingListError("title",
                    String.format("Shopping list with title '%s' not in a database\n", title.toUpperCase()));
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }
}
