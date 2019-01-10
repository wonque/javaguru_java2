package lv.java2.shopping_list.services.shoppinglist.removal;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListSharedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ShoppingListRemovalService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;
    @Autowired
    private ShoppingListItemRepository itemRepository;

    @Transactional
    public ShoppingListRemovalResponse remove(ShoppingListSharedRequest request) {
        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.
                findByAccountAndTitle(request.getAccount(), request.getTitle());

        if (optionalShoppingList.isPresent()) {
            return new ShoppingListRemovalResponse(shoppingListRepository.remove(optionalShoppingList.get()));
        } else {
            ShoppingListError error = new ShoppingListError("Remove Shopping list",
                    "No such shopping list founded!");
            return new ShoppingListRemovalResponse(error);
        }
    }
}
