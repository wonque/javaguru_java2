package services.shoppinglist.removal;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListSharedRequest;
import lv.java2.shopping_list.services.shoppinglist.removal.ShoppingListRemovalResponse;
import lv.java2.shopping_list.services.shoppinglist.removal.ShoppingListRemovalService;
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
public class ShoppingListRemovalServiceTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private ShoppingListItemRepository itemRepository;

    @InjectMocks
    private ShoppingListRemovalService removeService;

    private Account account = Mockito.mock(Account.class);
    ShoppingListSharedRequest request = new ShoppingListSharedRequest(account, "list1");


    @Test
    public void returnTrueIfShoppingListIsRemoved() {
        ShoppingList shoppingList = new ShoppingList();
        Mockito.when(shoppingListRepository.findByAccountAndTitle(request.getAccount(), request.getTitle()))
                .thenReturn(Optional.of(shoppingList));
        Mockito.when(shoppingListRepository.remove(shoppingList)).thenReturn(true);
        ShoppingListRemovalResponse response = removeService.remove(request);
        assertTrue(response.isListRemoved());
        assertNull(response.getError());

    }

    @Test
    public void returnErrorIfListNotRemoved() {
        Mockito.when(shoppingListRepository.findByAccountAndTitle(request.getAccount(), request.getTitle()))
                .thenReturn(Optional.empty());
        ShoppingListRemovalResponse response = removeService.remove(request);
        assertFalse(response.isListRemoved());
        assertNotNull(response.getError());
        assertEquals(response.getError().getField(), "Remove Shopping list");
        assertEquals(response.getError().getErrorDescription(),
                "No such shopping list founded!");
    }
}
