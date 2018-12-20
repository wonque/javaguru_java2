package services.item.update.price;


import lv.java2.shopping_list.domain.ShoppingListItem;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.ItemUpdateSharedRequest;
import lv.java2.shopping_list.services.item.update.price.validation.UpdateItemPriceValidator;
import lv.java2.shopping_list.services.item.update.price.validation.UpdateItemPriceValidatorImpl;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class UpdateItemPriceValidatorTest {

    private UpdateItemPriceValidator validator = new UpdateItemPriceValidatorImpl();
    private ShoppingListItem item = new ShoppingListItem();
    private ItemUpdateSharedRequest request = new ItemUpdateSharedRequest(item);


    @Test
    public void returnErrorIfEnteredPriceIsLessThanZero() {
        request.setPrice(-10);
        List<ShoppingListError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
    }

    @Test
    public void returnNoErrorIfPriceIsZeroOrBigger() {
        request.setPrice(10);
        List<ShoppingListError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
        assertEquals(0, errors.size());
    }
}
