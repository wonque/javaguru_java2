package item.services.update.description;

import lv.java2.shopping_list.item.repository.ItemRepository;
import lv.java2.shopping_list.item.domain.Item;
import lv.java2.shopping_list.item.services.ItemUpdateSharedRequest;
import lv.java2.shopping_list.item.services.update.ItemUpdateSharedResponse;
import lv.java2.shopping_list.item.services.update.description.UpdateItemDescriptionService;
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
    private Item item;
    private ItemUpdateSharedRequest updateSharedRequest;


    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private UpdateItemDescriptionService descriptionService = new UpdateItemDescriptionService();

    @Before
    public void init() {
        this.desc = "description";
        this.item = new Item();
        this.updateSharedRequest = new ItemUpdateSharedRequest(item);
        updateSharedRequest.setDescription(desc);
    }

    @Test
    public void returnNoErrorsIf1ItemIsUpdated() {
        Mockito.when(itemRepository.updateDescription(updateSharedRequest.getShoppingListItm(),
                updateSharedRequest.getDescription()))
                .thenReturn(true);
        ItemUpdateSharedResponse response = descriptionService.updateDescription(updateSharedRequest);
        assertTrue(response.isUpdated());
        assertNull(response.getError());
    }

    @Test
    public void returnErrorIfOneItemUpdateFailed() {
        Mockito.when(itemRepository.updateDescription(updateSharedRequest.getShoppingListItm(),
                updateSharedRequest.getDescription()))
                .thenReturn(false);
        ItemUpdateSharedResponse response = descriptionService.updateDescription(updateSharedRequest);
        assertFalse(response.isUpdated());
        assertNotNull(response.getError());
    }
}
