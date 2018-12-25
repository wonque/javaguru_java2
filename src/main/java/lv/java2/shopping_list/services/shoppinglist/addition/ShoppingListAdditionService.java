package lv.java2.shopping_list.services.shoppinglist.addition;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingListFactory;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListSharedRequest;
import lv.java2.shopping_list.services.shoppinglist.addition.validation.ShoppingListAdditionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShoppingListAdditionService {

    @Autowired
    private ShoppingListRepository repository;

    @Autowired
    private ShoppingListAdditionValidator validator;

    @Autowired
    private ShoppingListFactory shoppingListFactory;

    public ShoppingListAdditionResponse addList(ShoppingListSharedRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShoppingListAdditionResponse(errors);
        }
        ShoppingList newEntry = shoppingListFactory.createInstance(request.getAccount(), request.getTitle());
        repository.addToDataBase(newEntry);
        return new ShoppingListAdditionResponse(newEntry);
    }
}
