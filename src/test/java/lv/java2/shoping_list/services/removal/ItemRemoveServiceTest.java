package lv.java2.shoping_list.services.removal;

import lv.java2.shopping_list.item.repository.ItemRepository;
import lv.java2.shopping_list.item.domain.Item;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.item.services.ItemAddRemoveSharedRequest;
import lv.java2.shopping_list.item.services.removal.ItemRemoveResponse;
import lv.java2.shopping_list.item.services.removal.ItemRemoveService;
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
public class ItemRemoveServiceTest {

    private ShoppingList shoppingList;
    private ItemAddRemoveSharedRequest removeSharedRequest;
    private ItemRemoveResponse removeResponse;

    @Mock
    private ItemRepository repository;

    @InjectMocks
    private ItemRemoveService removeService = new ItemRemoveService();

    @Before
    public void init() {
        this.shoppingList = new ShoppingList();
        this.removeSharedRequest = new ItemAddRemoveSharedRequest(shoppingList, "milk");

    }

    @Test
    public void returnErrorIfItemNotFound() {
        Mockito.when(repository.findItemByTitle(removeSharedRequest.getShoppingList(), removeSharedRequest.getTitle()))
                .thenReturn(Optional.empty());
        removeResponse = removeService.removeSingleItem(removeSharedRequest);
        assertFalse(removeResponse.isRemoved());
        assertNotNull(removeResponse.getError());
        assertEquals("title", removeResponse.getError().getField());
        assertEquals("Item not found!", removeResponse.getError().getErrorDescription());
    }

    @Test
    public void returnNoErrorIfSingleItemRemoved() {
        Item item = new Item();
        item.setTitle("milk");
        ItemAddRemoveSharedRequest removeSharedRequest = new ItemAddRemoveSharedRequest(shoppingList, "milk");
        Mockito.when(repository.findItemByTitle(removeSharedRequest.getShoppingList(), removeSharedRequest.getTitle()))
                .thenReturn(Optional.of(item));
        removeResponse = removeService.removeSingleItem(removeSharedRequest);
        assertNull(removeResponse.getError());
        assertTrue(removeResponse.isRemoved());
    }

    @Test
    public void returnErrorIfZeroItemsWereRemoved() {
        Mockito.when(repository.removeAllItems(removeSharedRequest.getShoppingList()))
                .thenReturn(0);
        removeResponse = removeService.removeAllItems(removeSharedRequest);
        assertFalse(removeResponse.isRemoved());
        assertEquals(0, removeResponse.getTotalItemsRemoved());
        assertEquals("Shopping list", removeResponse.getError().getField());
        assertEquals("Zero items were removed!", removeResponse.getError().getErrorDescription());

    }

    @Test
    public void returnNoErrorIfAllItemsRemoved() {
        Mockito.when(repository.removeAllItems(removeSharedRequest.getShoppingList()))
                .thenReturn(10);
        removeResponse = removeService.removeAllItems(removeSharedRequest);
        assertTrue(removeResponse.isRemoved());
        assertEquals(10, removeResponse.getTotalItemsRemoved());
        assertNull(removeResponse.getError());
    }
}
