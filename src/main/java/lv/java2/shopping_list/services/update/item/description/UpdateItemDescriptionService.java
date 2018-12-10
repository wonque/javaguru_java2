package lv.java2.shopping_list.services.update.item.description;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.ShoppingListItem;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateItemDescriptionService {

    @Autowired
    private ShoppingListItemRepository itemRepository;

    public UpdateItemDescriptionResponse updateDescription(UpdateItemDescriptionRequest request) {
        Optional<ShoppingListItem> optionalItem = itemRepository.findItemByTitle(request.getShoppingList(),
                request.getItemTitle());
        if (optionalItem.isPresent()) {
            boolean updated = itemRepository.updateDescription(optionalItem.get(), request.getItemDescription());
            return new UpdateItemDescriptionResponse(updated);
        } else {
            ShoppingListError error = new ShoppingListError("title", "Item not found!");
            return new UpdateItemDescriptionResponse(error);
        }
    }
}
