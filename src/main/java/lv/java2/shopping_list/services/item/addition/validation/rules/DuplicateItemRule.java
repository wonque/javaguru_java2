package lv.java2.shopping_list.services.item.addition.validation.rules;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.domain.item.ShoppingListItem;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DuplicateItemRule {

    @Autowired
    private ShoppingListItemRepository itemRepository;

    public Optional<ShoppingListError> execute(ShoppingList list, String title) {
        if (title != null) {
            Optional<ShoppingListItem> item = itemRepository.findItemByTitle(list, title);
            if (item.isPresent()) {
                ShoppingListError error = new ShoppingListError("title", "Item already in list!");
                return Optional.of(error);
            }
        }
        return Optional.empty();
    }
}
