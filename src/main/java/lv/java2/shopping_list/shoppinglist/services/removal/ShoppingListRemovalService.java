package lv.java2.shopping_list.shoppinglist.services.removal;

import lv.java2.shopping_list.shoppinglist.repository.ShoppingListRepository;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingListFactory;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListSharedRequest;
import lv.java2.shopping_list.shoppinglist.services.removal.validation.ShoppingListRemovalValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListRemovalService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;
    @Autowired
    private ShoppingListFactory factory;
    @Autowired
    private ShoppingListRemovalValidatorImpl validator;

    @Transactional
    public ShoppingListRemovalResponse remove(ShoppingListSharedRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShoppingListRemovalResponse(errors);
        }
        Optional<ShoppingList> founded = shoppingListRepository.findByAccountAndTitle(request.getAccount(), request.getTitle());
        return new ShoppingListRemovalResponse(true);
    }
}

