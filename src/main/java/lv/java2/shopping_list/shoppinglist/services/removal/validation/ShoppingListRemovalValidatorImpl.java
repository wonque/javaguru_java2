package lv.java2.shopping_list.shoppinglist.services.removal.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListSharedRequest;
import lv.java2.shopping_list.shoppinglist.services.validation.rules.NullAccountRule;
import lv.java2.shopping_list.shoppinglist.services.validation.rules.ShoppingListExistenceRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingListRemovalValidatorImpl implements ShoppingListRemovalValidator {

    @Autowired
    private NullAccountRule nullAccountRule;
    @Autowired
    private ShoppingListExistenceRule existenceRule;

    @Override
    public List<ShoppingListError> validate(ShoppingListSharedRequest removalRequest) {
        List<ShoppingListError> errors = new ArrayList<>();
        nullAccountRule.validate(removalRequest.getAccount()).ifPresent(errors::add);
//        if (errors.isEmpty()) {
//            existenceRule.apply(removalRequest.getAccount(), removalRequest.getTitle()).ifPresent(errors::add);
//        }
        return errors;
    }
}
