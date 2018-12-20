package services.item.update.price;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.ShoppingListItem;
import lv.java2.shopping_list.services.ShoppingListError;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateItemPriceServiceTest {

    private List<ShoppingListError> errorList;
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
        this.errorList = new ArrayList<>();
        this.item = new ShoppingListItem();
        this.request = new ItemUpdateSharedRequest(item);
        request.setPrice(10);
    }

    @Test
    public void returnNoErrorsIfItemPriceIsUpdated() {
        Mockito.when(validator.validate(request)).thenReturn(errorList);
        Mockito.when(itemRepository.updatePrice(request.getShoppingListItm(),
                BigDecimal.valueOf(10)))
                .thenReturn(1);
        ItemUpdateSharedResponse response = priceService.updatePrice(request);
        assertTrue(response.isUpdated());
        assertNull(response.getErrors());
    }

    @Test
    public void returnErrorIfPriceIsNegative() {
        request.setPrice(-10);
        Mockito.when(validator.validate(request)).thenCallRealMethod();
        ItemUpdateSharedResponse response = priceService.updatePrice(request);
        assertFalse(response.isUpdated());
        assertNotNull(response.getErrors());
        assertEquals(1, response.getErrors().size());
    }

    @Test
    public void returnErrorIfPriceIsPositiveButNotUpdated() {
        Mockito.when(validator.validate(request)).thenReturn(errorList);
        Mockito.when(itemRepository.updatePrice(request.getShoppingListItm(),
                BigDecimal.valueOf(10))).thenReturn(0);
        ItemUpdateSharedResponse response = priceService.updatePrice(request);
        assertFalse(response.isUpdated());
        assertNotNull(response.getErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("price", response.getErrors().get(0).getField());
        assertEquals("Unable to update price!", response.getErrors().get(0).getErrorDescription());


    }


}
