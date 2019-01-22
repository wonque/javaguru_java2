package item.services.update.price;


import lv.java2.shopping_list.item.domain.Item;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.item.services.ItemUpdateSharedRequest;
import lv.java2.shopping_list.item.services.update.price.validation.UpdateItemPriceValidator;
import lv.java2.shopping_list.item.services.update.price.validation.UpdateItemPriceValidatorImpl;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class UpdateItemPriceValidatorTest {

    private UpdateItemPriceValidator validator = new UpdateItemPriceValidatorImpl();
    private Item item = new Item();
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
