package services.shoppinglist.removal;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListSharedRequest;
import lv.java2.shopping_list.services.shoppinglist.removal.RemoveShoppingListResponse;
import lv.java2.shopping_list.services.shoppinglist.removal.RemoveShoppingListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class RemoveShoppingListServiceTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private ShoppingListItemRepository itemRepository;

    @InjectMocks
    private RemoveShoppingListService removeService;

    private Account account = Mockito.mock(Account.class);
    ShoppingListSharedRequest request = new ShoppingListSharedRequest(account, "list1");


    @Test
    public void returnTrueIfShoppingListIsRemoved() {
        ShoppingList shoppingList = new ShoppingList();
        Mockito.when(shoppingListRepository.findByAccountAndTitle(request.getAccount(), request.getTitle()))
                .thenReturn(Optional.of(shoppingList));
        Mockito.when(itemRepository.removeAllItemsFromShoppingList(shoppingList)).thenReturn(5);
        Mockito.when(shoppingListRepository.remove(shoppingList)).thenReturn(true);
        RemoveShoppingListResponse response = removeService.remove(request);
        assertTrue(response.isListRemoved());
        assertNull(response.getError());

    }

    @Test
    public void returnErrorIfListNotRemoved() {
        Mockito.when(shoppingListRepository.findByAccountAndTitle(request.getAccount(), request.getTitle()))
                .thenReturn(Optional.empty());
        RemoveShoppingListResponse response = removeService.remove(request);
        assertFalse(response.isListRemoved());
        assertNotNull(response.getError());
        assertEquals(response.getError().getField(), "Remove Shopping list");
        assertEquals(response.getError().getErrorDescription(),
                "No such shopping list founded!");
    }
}
