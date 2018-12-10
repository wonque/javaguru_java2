package lv.java2.shopping_list.services.add.item.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.item.ItemAdditionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemAdditionValidatorImpl implements ItemAdditionValidator {

    @Autowired
    private ItemAdditionRules itemRules;


    @Override
    public List<ShoppingListError> validate(ItemAdditionRequest additionRequest) {
        List<ShoppingListError> errors = new ArrayList<>();
        itemRules.emptyTitle(additionRequest.getTitle()).ifPresent(errors::add);
        itemRules.duplicateItem(additionRequest.getShoppingList(), additionRequest.getTitle()).ifPresent(errors::add);
        return errors;
    }


}
