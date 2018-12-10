package lv.java2.shopping_list.services.add.shoppinglist;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.builders.ShoppingListBuilder;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.shoppinglist.validation.ShoppingListAdditionValidator;
import lv.java2.shopping_list.services.add.shoppinglist.validation.ShoppingListAdditionValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ShoppingListAdditionService {

    @Autowired
    private ShoppingListRepository repository;

    @Autowired
    private ShoppingListAdditionValidator validator;

    @Autowired
    private ShoppingListBuilder shoppingListBuilder;

    public ShoppingListAdditionResponse addList(ShoppingListAdditionRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShoppingListAdditionResponse(errors);
        }
        ShoppingList newEntry = shoppingListBuilder.createInstance(request.getAccount(), request.getTitle());
        repository.addToDataBase(newEntry);
        return new ShoppingListAdditionResponse(newEntry);
    }
}
