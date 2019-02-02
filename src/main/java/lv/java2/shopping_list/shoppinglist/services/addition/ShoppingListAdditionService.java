package lv.java2.shopping_list.shoppinglist.services.addition;

import lv.java2.shopping_list.shoppinglist.repository.ShoppingListRepository;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingListFactory;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListSharedRequest;
import lv.java2.shopping_list.shoppinglist.services.addition.validation.ShoppingListAdditionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShoppingListAdditionService {

    @Autowired
    private ShoppingListRepository repository;

    @Autowired
    private ShoppingListAdditionValidator validator;

    @Autowired
    private ShoppingListFactory shoppingListFactory;

    @Transactional
    public ShoppingListAdditionResponse addList(ShoppingListSharedRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShoppingListAdditionResponse(errors);
        }
        ShoppingList newEntry = shoppingListFactory.createInstance(request.getAccount(), request.getTitle());
        repository.save(newEntry);
        return new ShoppingListAdditionResponse(newEntry);
    }
}
