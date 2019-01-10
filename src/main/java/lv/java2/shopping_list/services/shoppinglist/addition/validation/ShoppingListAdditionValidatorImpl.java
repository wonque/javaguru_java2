package lv.java2.shopping_list.services.shoppinglist.addition.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.sharedrules.EmptyTitleSharedRule;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListSharedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListAdditionValidatorImpl implements ShoppingListAdditionValidator {


    @Autowired
    private ShoppingListValidationRules rules;
    @Autowired
    private EmptyTitleSharedRule titleSharedRule;

    @Override
    public List<ShoppingListError> validate(ShoppingListSharedRequest additionRequest) {
        List<ShoppingListError> errors = new ArrayList<>();
        titleSharedRule.execute(additionRequest.getTitle(), "title").ifPresent(errors::add);
        if (!errors.isEmpty()) {
            return errors;
        }
        rules.duplicateEntryRule(additionRequest.getAccount(), additionRequest.getTitle()).ifPresent(errors::add);
        return errors;
    }
}
