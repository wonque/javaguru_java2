package lv.java2.shopping_list.shoppinglist.services.validation.removal;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListSharedRequest;
import lv.java2.shopping_list.shoppinglist.services.validation.rules.NullAccountRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingListRemovalValidatorImpl implements ShoppingListRemovalValidator {

    @Autowired
    private NullAccountRule nullAccountRule;

    @Override
    public List<ShoppingListError> validate(ShoppingListSharedRequest removalRequest) {
        List<ShoppingListError> errors = new ArrayList<>();
        nullAccountRule.validate(removalRequest.getAccount()).ifPresent(errors::add);
        return errors;
    }
}
