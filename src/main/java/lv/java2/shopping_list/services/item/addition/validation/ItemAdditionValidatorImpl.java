package lv.java2.shopping_list.services.item.addition.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.addition.validation.rules.DuplicateItemRule;
import lv.java2.shopping_list.services.item.ItemAddRemoveSharedRequest;
import lv.java2.shopping_list.services.sharedrules.EmptyTitleSharedRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemAdditionValidatorImpl implements ItemAdditionValidator {

    @Autowired
    private DuplicateItemRule duplicateItemRule;
    @Autowired
    private EmptyTitleSharedRule titleSharedRule;

    @Override
    public List<ShoppingListError> validate(ItemAddRemoveSharedRequest additionReq) {
        List<ShoppingListError> errors = new ArrayList<>();
        titleSharedRule.execute(additionReq.getTitle(), "title").ifPresent(errors::add);
        duplicateItemRule.execute(additionReq.getShoppingList(), additionReq.getTitle()).ifPresent(errors::add);
        return errors;
    }


}
