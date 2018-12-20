package lv.java2.shopping_list.services.shoppinglist.removal;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListSharedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class RemoveShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;
    @Autowired
    private ShoppingListItemRepository itemRepository;

    @Transactional
    public RemoveShoppingListResponse remove(ShoppingListSharedRequest request) {
        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.
                findByAccountAndTitle(request.getAccount(), request.getTitle());

        if (optionalShoppingList.isPresent()) {
            itemRepository.removeAllItemsFromShoppingList(optionalShoppingList.get());
            return new RemoveShoppingListResponse(shoppingListRepository.remove(optionalShoppingList.get()));
        } else {
            ShoppingListError error = new ShoppingListError("Remove Shopping list",
                    "No such shopping list founded!");
            return new RemoveShoppingListResponse(error);
        }
    }
}
