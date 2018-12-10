package lv.java2.shopping_list.services.add.item;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListItem;
import lv.java2.shopping_list.domain.builders.ShoppingListItemBuilder;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.item.validation.ItemAdditionValidator;
import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionResponse;
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
    private ShoppingListItemBuilder itemBuilder;

    public ItemAdditionResponse addItem(ItemAdditionRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ItemAdditionResponse(errors);
        }
        ShoppingListItem newEntry = itemBuilder.createWithTitle(request.getShoppingList(), request.getTitle());
        itemRepository.addItemToShoppingList(newEntry);
        return new ItemAdditionResponse(newEntry);
    }
}
