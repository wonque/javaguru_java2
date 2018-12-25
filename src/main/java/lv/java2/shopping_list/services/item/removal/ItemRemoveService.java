package lv.java2.shopping_list.services.item.removal;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.item.ShoppingListItem;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.ItemAddRemoveSharedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemRemoveService {

    @Autowired
    private ShoppingListItemRepository itemRepository;

    public ItemRemoveResponse removeSingleItem(ItemAddRemoveSharedRequest request) {
        Optional<ShoppingListItem> founded = itemRepository.findItemByTitle(request.getShoppingList(),
                request.getTitle());
        if (founded.isPresent()) {
            itemRepository.removeSingleItem(founded.get());
            return new ItemRemoveResponse(true);
        } else {
            ShoppingListError error = new ShoppingListError("title",
                    "Item not found!");
            return new ItemRemoveResponse(error);
        }
    }

    public ItemRemoveResponse removeAllItems(ItemAddRemoveSharedRequest request) {
        int rowsAffected = itemRepository.removeAllItems(request.getShoppingList());
        if (rowsAffected > 0) {
            ItemRemoveResponse removeResponse = new ItemRemoveResponse(true);
            removeResponse.setTotalItemsRemoved(rowsAffected);
            return removeResponse;
        } else {
            ShoppingListError error = new ShoppingListError("Shopping list",
                    "Zero items were removed!");
            return new ItemRemoveResponse(error);
        }
    }
}
