package lv.java2.shopping_list.item.services.update.description;

import lv.java2.shopping_list.item.repository.ItemRepository;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.item.services.ItemUpdateSharedRequest;
import lv.java2.shopping_list.item.services.update.ItemUpdateSharedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateItemDescriptionService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemUpdateSharedResponse updateDescription(ItemUpdateSharedRequest request) {
        boolean isUpdated = itemRepository.updateDescription(request.getShoppingListItm(), request.getDescription());
        if (isUpdated) {
            return new ItemUpdateSharedResponse(isUpdated);
        } else {
            return createNoItemsUpdatedResponse();
        }
    }

    private ItemUpdateSharedResponse createNoItemsUpdatedResponse() {
        ShoppingListError error = new ShoppingListError("description",
                "No items were updated!");
        return new ItemUpdateSharedResponse(error);
    }
}
