package lv.java2.shoping_list.shoppinglist.services.addition;

import lv.java2.shopping_list.shoppinglist.repository.ShoppingListRepository;

import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingListFactory;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListSharedRequest;
import lv.java2.shopping_list.shoppinglist.services.addition.ShoppingListAdditionResponse;
import lv.java2.shopping_list.shoppinglist.services.addition.ShoppingListAdditionService;
import lv.java2.shopping_list.shoppinglist.services.addition.validation.ShoppingListAdditionValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;


@RunWith(MockitoJUnitRunner.class)
public class ShoppingListAdditionServiceTest {

    @Mock
    private ShoppingListRepository repository;

    @Mock
    private ShoppingListAdditionValidator validator;

    @Mock
    private ShoppingListFactory itemBuilder;

    private ShoppingListSharedRequest request;
    private List<ShoppingListError> errors;
    private Account account;

    @Before
    public void buildRequestAndErrors() {
        this.account = new Account();
        account.setEmail("login");
        account.setPassword("password");
        this.request = new ShoppingListSharedRequest(account, "testList");
        this.errors = new ArrayList<>();
    }

    @InjectMocks
    private ShoppingListAdditionService additionService = new ShoppingListAdditionService();

    @Test
    public void shouldReturnResponseWithListOfErrors() {
        errors.add(new ShoppingListError("title", "description"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ShoppingListAdditionResponse response = additionService.addList(request);
        assertFalse(response.isSuccess());
        assertEquals(1, response.getShoppingListErrors().size());
        Mockito.verifyZeroInteractions(repository);
    }

    @Test
    public void shouldReturnResponseWithAccount() {
        ShoppingList shoppingList = Mockito.mock(ShoppingList.class);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(itemBuilder.createInstance(account,"testList")).thenReturn(shoppingList);
        Mockito.when(repository.save(shoppingList)).thenReturn(shoppingList);
        ShoppingListAdditionResponse response = additionService.addList(request);
        assertTrue(response.isSuccess());
        assertNotNull(response.getAddedList());
    }
}

