package lv.java2.shopping_list.item.services.addition.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.item.services.addition.validation.rules.DuplicateItemRule;
import lv.java2.shopping_list.item.services.ItemAddRemoveSharedRequest;
import lv.java2.shopping_list.sharedrules.EmptyStringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemAdditionValidatorImpl implements ItemAdditionValidator {

    @Autowired
    private DuplicateItemRule duplicateItemRule;
    @Autowired
    private EmptyStringRule titleSharedRule;

    @Override
    public List<ShoppingListError> validate(ItemAddRemoveSharedRequest additionReq) {
        List<ShoppingListError> errors = new ArrayList<>();
        titleSharedRule.execute(additionReq.getTitle(), "title").ifPresent(errors::add);
        duplicateItemRule.execute(additionReq.getShoppingList(), additionReq.getTitle()).ifPresent(errors::add);
        return errors;
    }


}
