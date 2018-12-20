package lv.java2.shopping_list.services.item.update.description;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.ItemUpdateSharedRequest;
import lv.java2.shopping_list.services.item.update.ItemUpdateSharedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateItemDescriptionService {

    @Autowired
    private ShoppingListItemRepository itemRepository;

    public ItemUpdateSharedResponse updateDescription(ItemUpdateSharedRequest request) {
        List<ShoppingListError> errors = new ArrayList<>();
        int rowAffected = itemRepository.updateDescription(request.getShoppingListItm(), request.getDescription());
        if (rowAffected > 0) {
            return new ItemUpdateSharedResponse(true);
        } else {
            ShoppingListError error = new ShoppingListError("description",
                    "No items were updated!");
            errors.add(error);
            return new ItemUpdateSharedResponse(errors);
        }
    }
}
