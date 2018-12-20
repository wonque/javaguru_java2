package services.item.update.description;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.ShoppingListItem;
import lv.java2.shopping_list.services.item.ItemUpdateSharedRequest;
import lv.java2.shopping_list.services.item.update.ItemUpdateSharedResponse;
import lv.java2.shopping_list.services.item.update.description.UpdateItemDescriptionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateItemDescriptionServiceTest {

    private String desc;
    private ShoppingListItem item;
    private ItemUpdateSharedRequest updateSharedRequest;


    @Mock
    private ShoppingListItemRepository itemRepository;

    @InjectMocks
    private UpdateItemDescriptionService descriptionService = new UpdateItemDescriptionService();

    @Before
    public void init() {
        this.desc = "description";
        this.item = new ShoppingListItem();
        this.updateSharedRequest = new ItemUpdateSharedRequest(item);
        updateSharedRequest.setDescription(desc);
    }

    @Test
    public void returnNoErrorsIf1ItemIsUpdated() {
        Mockito.when(itemRepository.updateDescription(updateSharedRequest.getShoppingListItm(),
                updateSharedRequest.getDescription()))
                .thenReturn(1);
        ItemUpdateSharedResponse response = descriptionService.updateDescription(updateSharedRequest);
        assertTrue(response.isUpdated());
        assertNull(response.getErrors());
    }

    @Test
    public void returnErrorIfOneItemUpdateFailed() {
        Mockito.when(itemRepository.updateDescription(updateSharedRequest.getShoppingListItm(),
                updateSharedRequest.getDescription()))
                .thenReturn(0);
        ItemUpdateSharedResponse response = descriptionService.updateDescription(updateSharedRequest);
        assertFalse(response.isUpdated());
        assertNotNull(response.getErrors());
        assertEquals(1, response.getErrors().size());
    }
}
