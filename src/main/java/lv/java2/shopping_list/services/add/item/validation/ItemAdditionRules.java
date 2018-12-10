package lv.java2.shopping_list.services.add.item.validation;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListItem;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemAdditionRules {

    @Autowired
    private ShoppingListItemRepository itemRepository;

    public Optional<ShoppingListError> emptyTitle(String title) {
        if (title == null || title.isEmpty() || title.matches("\\s+")) {
            ShoppingListError error = new ShoppingListError("title", "Empty title not allowed!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }

    public Optional<ShoppingListError> duplicateItem(ShoppingList list, String title) {
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
