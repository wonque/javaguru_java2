package services.item.update.price;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.item.ShoppingListItem;
import lv.java2.shopping_list.services.item.ItemUpdateSharedRequest;
import lv.java2.shopping_list.services.item.update.ItemUpdateSharedResponse;
import lv.java2.shopping_list.services.item.update.price.UpdateItemPriceService;
import lv.java2.shopping_list.services.item.update.price.validation.UpdateItemPriceValidatorImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateItemPriceServiceTest {

    private ShoppingListItem item;
    private ItemUpdateSharedRequest request;


    @Mock
    private UpdateItemPriceValidatorImpl validator;
    @Mock
    private ShoppingListItemRepository itemRepository;

    @InjectMocks
    private UpdateItemPriceService priceService;

    @Before
    public void init() {
        this.item = new ShoppingListItem();
        this.request = new ItemUpdateSharedRequest(item);
        request.setBigDecimalPrice(10);
    }

    @Test
    public void returnNoErrorsIfItemPriceIsUpdated() {
        Mockito.when(validator.validate(request)).thenReturn(Optional.empty());
        Mockito.when(itemRepository.updatePrice(request.getShoppingListItm(),
                request.getPrice()))
                .thenReturn(true);
        ItemUpdateSharedResponse response = priceService.updatePrice(request);
        assertTrue(response.isUpdated());
        assertNull(response.getError());
    }

    @Test
    public void returnErrorIfPriceIsNegative() {
        request.setBigDecimalPrice(-10);
        Mockito.when(validator.validate(request)).thenCallRealMethod();
        ItemUpdateSharedResponse response = priceService.updatePrice(request);
        assertFalse(response.isUpdated());
        assertNotNull(response.getError());
    }

    @Test
    public void returnErrorIfPriceIsPositiveButNotUpdated() {
        Mockito.when(validator.validate(request)).thenReturn(Optional.empty());
        Mockito.when(itemRepository.updatePrice(request.getShoppingListItm(),
                request.getPrice())).thenReturn(false);
        ItemUpdateSharedResponse response = priceService.updatePrice(request);
        assertFalse(response.isUpdated());
        assertNotNull(response.getError());
        assertEquals("price", response.getError().getField());
        assertEquals("Unable to update price!", response.getError().getErrorDescription());
    }
}
