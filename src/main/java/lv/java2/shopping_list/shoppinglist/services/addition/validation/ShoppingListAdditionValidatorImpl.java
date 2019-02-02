package lv.java2.shopping_list.shoppinglist.services.addition.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.sharedrules.EmptyStringRule;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListSharedRequest;
import lv.java2.shopping_list.shoppinglist.services.validation.rules.AccountNotExistsRule;
import lv.java2.shopping_list.shoppinglist.services.validation.rules.DuplicateEntryRule;
import lv.java2.shopping_list.shoppinglist.services.validation.rules.NullAccountRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListAdditionValidatorImpl implements ShoppingListAdditionValidator {

    @Autowired
    private NullAccountRule nullAccountRule;
    @Autowired
    private DuplicateEntryRule duplicateEntryRule;
    @Autowired
    private AccountNotExistsRule accountNotExistsRule;
    @Autowired
    private EmptyStringRule titleSharedRule;

    @Override
    public List<ShoppingListError> validate(ShoppingListSharedRequest additionRequest) {
        List<ShoppingListError> errors = new ArrayList<>();
        titleSharedRule.execute(additionRequest.getTitle(), "title").ifPresent(errors::add);
        nullAccountRule.validate(additionRequest.getAccount()).ifPresent(errors::add);
        if (!errors.isEmpty()) {
            return errors;
        }
        accountNotExistsRule.validate(additionRequest.getAccount()).ifPresent(errors::add);
        duplicateEntryRule.validate(additionRequest.getAccount(), additionRequest.getTitle()).ifPresent(errors::add);
        return errors;
    }


}
