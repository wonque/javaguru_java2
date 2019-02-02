package lv.java2.shopping_list.item.services.removal;

import lv.java2.shopping_list.item.repository.ItemRepository;
import lv.java2.shopping_list.item.domain.Item;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.item.services.ItemAddRemoveSharedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemRemoveService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemRemoveResponse removeSingleItem(ItemAddRemoveSharedRequest request) {
        Optional<Item> founded = itemRepository.findItemByTitle(request.getShoppingList(),
                request.getTitle());
        if (founded.isPresent()) {
            itemRepository.delete(founded.get());
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
