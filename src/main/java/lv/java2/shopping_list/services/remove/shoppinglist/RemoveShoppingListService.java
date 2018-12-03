package lv.java2.shopping_list.services.remove.shoppinglist;


import lv.java2.shopping_list.db.ShoppingListRepository;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RemoveShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private RemoveShoppingListValidator validator;


    public RemoveShoppingListResponse remove(RemoveShoppingListRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveShoppingListResponse(errors);
        }
        ShoppingList listToDelete = getListToDelete(request);
        return new RemoveShoppingListResponse(shoppingListRepository.remove(listToDelete));
    }

    private ShoppingList getListToDelete(RemoveShoppingListRequest request) {
        ShoppingList listToDelete = null;
        Optional<ShoppingList> foundedList = shoppingListRepository.getByTitle(request.getTitle());
        if (foundedList.isPresent()) {
            listToDelete = foundedList.get();
        }
        return listToDelete;
    }
}
