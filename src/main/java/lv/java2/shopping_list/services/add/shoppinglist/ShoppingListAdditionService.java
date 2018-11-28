package lv.java2.shopping_list.services.add.shoppinglist;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.factories.ShoppingListFactory;
import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.add.shoppinglist.validation.ShoppingListAdditionValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ShoppingListAdditionService {


    @Autowired
    private ShoppingListAdditionValidatorImpl validator;

    @Autowired
    private ShoppingListFactory shoppingListFactory;

    public ShoppingListAdditionResponse addList(ShoppingListAdditionRequest request) {
        List<Error> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShoppingListAdditionResponse(errors);
        }
        ShoppingList newEntry = shoppingListFactory.saveListToBase(request.getTitle());
        return new ShoppingListAdditionResponse(newEntry);
    }

}
