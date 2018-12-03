package lv.java2.shopping_list.services.add.shoppinglist.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingListAdditionValidatorImpl implements ShoppingListAdditionValidator {


    @Autowired
    private ShoppingListValidationRules rules;


    @Override
    public List<ShoppingListError> validate(ShoppingListAdditionRequest additionRequest) {
        List<ShoppingListError> errors = new ArrayList<>();
        rules.emptyTitleRule(additionRequest.getTitle()).ifPresent(errors::add);
        rules.duplicateEntryRule(additionRequest.getTitle()).ifPresent(errors::add);
        return errors;
    }
}
