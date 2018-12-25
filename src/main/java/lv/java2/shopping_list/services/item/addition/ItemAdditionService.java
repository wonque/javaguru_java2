package lv.java2.shopping_list.services.item.addition;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.item.ShoppingListItem;
import lv.java2.shopping_list.domain.item.ShoppingListItemFactory;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.addition.validation.ItemAdditionValidator;
import lv.java2.shopping_list.services.item.ItemAddRemoveSharedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemAdditionService {

    @Autowired
    private ShoppingListItemRepository itemRepository;

    @Autowired
    private ItemAdditionValidator validator;

    @Autowired
    private ShoppingListItemFactory itemBuilder;

    public ItemAdditionResponse addItem(ItemAddRemoveSharedRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ItemAdditionResponse(errors);
        }
        ShoppingListItem newEntry = itemBuilder.createWithTitle(request.getShoppingList(), request.getTitle());
        itemRepository.addItemToShoppingList(newEntry);
        return new ItemAdditionResponse(newEntry);
    }
}
