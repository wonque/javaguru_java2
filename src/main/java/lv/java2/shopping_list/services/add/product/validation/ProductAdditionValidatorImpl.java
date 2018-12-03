package lv.java2.shopping_list.services.add.product.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.product.ProductAdditionRequest;
import lv.java2.shopping_list.services.add.product.validation.rules.FirstCharacterRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.java2.shopping_list.services.add.product.validation.rules.DuplicateProductTitleRule;
import lv.java2.shopping_list.services.add.product.validation.rules.EmptyTitleRule;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductAdditionValidatorImpl implements ProductAdditionValidator {


    @Autowired
    private EmptyTitleRule emptyTitleRule;
    @Autowired
    private DuplicateProductTitleRule duplicateProductTitleRule;
    @Autowired
    private FirstCharacterRule firstCharacterRule;

    @Override
    public List<ShoppingListError> validate(ProductAdditionRequest addProductAdditionRequest) {
        List<ShoppingListError> errors = new ArrayList<>();
        emptyTitleRule.execute(addProductAdditionRequest.getTitle()).ifPresent(errors::add);
        firstCharacterRule.execute(addProductAdditionRequest.getTitle()).ifPresent(errors::add);
        duplicateProductTitleRule.execute(addProductAdditionRequest.getTitle()).ifPresent(errors::add);
        return errors;
    }
}
