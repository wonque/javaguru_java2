package services.item.update.price;


import lv.java2.shopping_list.domain.item.ShoppingListItem;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.ItemUpdateSharedRequest;
import lv.java2.shopping_list.services.item.update.price.validation.UpdateItemPriceValidator;
import lv.java2.shopping_list.services.item.update.price.validation.UpdateItemPriceValidatorImpl;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class UpdateItemPriceValidatorTest {

    private UpdateItemPriceValidator validator = new UpdateItemPriceValidatorImpl();
    private ShoppingListItem item = new ShoppingListItem();
    private ItemUpdateSharedRequest request = new ItemUpdateSharedRequest(item);


    @Test
    public void returnErrorIfEnteredPriceIsLessThanZero() {
        request.setBigDecimalPrice(-10);
        Optional<ShoppingListError> error = validator.validate(request);
        assertTrue(error.isPresent());
    }

    @Test
    public void returnNoErrorIfPriceIsZeroOrBigger() {
        request.setBigDecimalPrice(10);
        Optional<ShoppingListError> error = validator.validate(request);
        assertFalse(error.isPresent());
    }
}
