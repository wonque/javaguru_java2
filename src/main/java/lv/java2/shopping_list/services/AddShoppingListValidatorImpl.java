package lv.java2.shopping_list.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddShoppingListValidatorImpl implements AddShoppingListValidator {


    @Autowired
    private AddShoppingListRules rules;

    @Autowired
    private ErrorFactory errorFactory;


    @Override
    public List<Error> validate(AddShoppingListRequest request) {
        List<Error> errors = new ArrayList<>();
        rules.emptyTitleRule(request.getTitle()).ifPresent(errors::add);
        return errors;
    }
}
