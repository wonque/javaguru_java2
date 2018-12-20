package lv.java2.shopping_list.services.product.adition.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.product.adition.ProductAdditionRequest;
import lv.java2.shopping_list.services.product.adition.validation.rules.FirstCharacterRule;
import lv.java2.shopping_list.services.EmptyTitleSharedRule;
import org.springframework.beans.factory.annotation.Autowired;
import lv.java2.shopping_list.services.product.adition.validation.rules.DuplicateProductTitleRule;

import java.util.ArrayList;
import java.util.List;

//@Component
public class ProductAdditionValidatorImpl implements ProductAdditionValidator {


    @Autowired
    private EmptyTitleSharedRule emptyTitleRule;
    @Autowired
    private DuplicateProductTitleRule duplicateProductTitleRule;
    @Autowired
    private FirstCharacterRule firstCharacterRule;

    @Override
    public List<ShoppingListError> validate(ProductAdditionRequest productAdditionRequest) {
        List<ShoppingListError> errors = new ArrayList<>();
        emptyTitleRule.execute(productAdditionRequest.getTitle(), "title").ifPresent(errors::add);
        firstCharacterRule.execute(productAdditionRequest.getTitle()).ifPresent(errors::add);
        duplicateProductTitleRule.execute(productAdditionRequest.getTitle()).ifPresent(errors::add);
        return errors;
    }
}
