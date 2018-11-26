package lv.java2.shopping_list.services;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private AddShoppingListValidator validator;

    @Autowired
    private ShoppingListFactory shoppingListFactory;

    public AddShoppingListResponse addList(AddShoppingListRequest request) {
        List<Error> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddShoppingListResponse(errors);
        }

        ShoppingList newEntry = shoppingListFactory.createNewShoppingList(request.getTitle());

        newEntry = shoppingListRepository.addToDataBase(newEntry);

        return new AddShoppingListResponse(newEntry.getId());
    }

}
