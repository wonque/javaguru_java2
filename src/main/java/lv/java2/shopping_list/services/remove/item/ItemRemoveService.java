package lv.java2.shopping_list.services.remove.item;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.ShoppingListItem;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ItemRemoveService {

    @Autowired
    private ShoppingListItemRepository itemRepository;

    @Transactional
    public ItemRemoveResponse removeSingleItem(ItemRemoveRequest removeRequest) {
        Optional<ShoppingListItem> itemOptional = itemRepository.findItemByTitle(removeRequest.getShoppingList(),
                removeRequest.getTitle());
        if (itemOptional.isPresent()) {
            boolean removed = itemRepository.removeSingleItem(itemOptional.get());
            return new ItemRemoveResponse(removed);
        } else {
            ShoppingListError error = new ShoppingListError("title", "Item not found!");
            return new ItemRemoveResponse(error);
        }
    }

    @Transactional
    public ItemRemoveResponse removeAllItems(ItemRemoveRequest removeRequest) {
        int rowsAffected = itemRepository.removeAllItemsFromShoppingList(removeRequest.getShoppingList());
        if (rowsAffected == 0) {
            ShoppingListError error = new ShoppingListError("Shopping list",
                    "No items were removed - current shopping list is empty");
            return new ItemRemoveResponse(error);
        } else {
            return new ItemRemoveResponse(true);
        }
    }
}
