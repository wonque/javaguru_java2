package lv.java2.shopping_list.services.remove.shoppinglist;

import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RemoveShoppingListValidatorImpl implements RemoveShoppingListValidator {

    @Autowired
    private RemoveShoppingListRules rules;

    @Override
    public List<ShoppingListError> validate(RemoveShoppingListRequest request) {
        List<ShoppingListError> errors = new ArrayList<>();
        rules.nullTitleRule(request.getTitle()).ifPresent(errors::add);
        rules.shoppingListPresenceInDataBaseRule(request.getTitle()).ifPresent(errors::add);
        return errors;
    }
}
