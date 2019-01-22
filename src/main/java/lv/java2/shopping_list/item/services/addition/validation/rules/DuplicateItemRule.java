package lv.java2.shopping_list.item.services.addition.validation.rules;

import lv.java2.shopping_list.item.repository.ItemRepository;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.item.domain.Item;
import lv.java2.shopping_list.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DuplicateItemRule {

    @Autowired
    private ItemRepository itemRepository;

    public Optional<ShoppingListError> execute(ShoppingList list, String title) {
        if (title != null) {
            Optional<Item> item = itemRepository.findItemByTitle(list, title);
            if (item.isPresent()) {
                ShoppingListError error = new ShoppingListError("title", "Item already in list!");
                return Optional.of(error);
            }
        }
        return Optional.empty();
    }
}
