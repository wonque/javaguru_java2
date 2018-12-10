package lv.java2.shopping_list.services.remove.product;

import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class RemoveProductValidatorImpl implements RemoveProductValidator {

    @Autowired
    private RemoveProductRules rules;

    @Override
    public List<ShoppingListError> validate(RemoveProductRequest request) {
        List<ShoppingListError> errors = new ArrayList<>();
        rules.nullTitleRule(request.getTitle()).ifPresent(errors::add);
        rules.productPresenceInDataBaseRule(request.getTitle()).ifPresent(errors::add);
        return errors;
    }
}
