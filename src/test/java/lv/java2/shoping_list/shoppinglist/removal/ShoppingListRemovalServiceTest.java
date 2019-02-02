package lv.java2.shoping_list.shoppinglist.removal;

import lv.java2.shopping_list.shoppinglist.domain.ShoppingListFactory;
import lv.java2.shopping_list.shoppinglist.repository.ShoppingListRepository;
import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListSharedRequest;
import lv.java2.shopping_list.shoppinglist.services.removal.ShoppingListRemovalResponse;
import lv.java2.shopping_list.shoppinglist.services.removal.ShoppingListRemovalService;
import lv.java2.shopping_list.shoppinglist.services.removal.validation.ShoppingListRemovalValidatorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

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
    private ShoppingListFactory shoppingListFactory;

    @Mock
    private ShoppingListRemovalValidatorImpl validator;

    @InjectMocks
    private ShoppingListRemovalService removeService;

    private Account account = Mockito.mock(Account.class);
    private ShoppingListSharedRequest request = new ShoppingListSharedRequest(account, "list1");
    private List<ShoppingListError> errors = new ArrayList<>();


    @Test
    public void returnTrueIfShoppingListIsRemoved() {
        ShoppingList shoppingList = new ShoppingList();
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(shoppingListRepository.findByAccountAndTitle(request.getAccount(), request.getTitle()))
                .thenReturn(Optional.of(shoppingList));
        Mockito.when(shoppingListFactory.createInstance(request.getAccount(), request.getTitle()))
                .thenReturn(shoppingList);
        ShoppingListRemovalResponse response = removeService.remove(request);
        assertTrue(response.isListRemoved());
        assertNull(response.getErrors());
    }

    @Test
    public void returnErrorIfListNotRemoved() {
        errors.add(new ShoppingListError("title", "description"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(shoppingListRepository.findByAccountAndTitle(request.getAccount(), request.getTitle()))
                .thenReturn(Optional.empty());
        ShoppingListRemovalResponse response = removeService.remove(request);
        assertFalse(response.isListRemoved());
        assertNotNull(response.getErrors());
        assertEquals(1, response.getErrors().size());
    }
}
