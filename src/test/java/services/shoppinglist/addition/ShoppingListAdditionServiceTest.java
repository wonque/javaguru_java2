package services.shoppinglist.addition;

import lv.java2.shopping_list.db.ShoppingListRepository;

import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.factories.ShoppingListFactory;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListSharedRequest;
import lv.java2.shopping_list.services.shoppinglist.addition.ShoppingListAdditionResponse;
import lv.java2.shopping_list.services.shoppinglist.addition.ShoppingListAdditionService;
import lv.java2.shopping_list.services.shoppinglist.addition.validation.ShoppingListAdditionValidator;
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
        account.setLogin("login");
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
        Mockito.when(repository.addToDataBase(shoppingList)).thenReturn(shoppingList);
        ShoppingListAdditionResponse response = additionService.addList(request);
        assertTrue(response.isSuccess());
        assertNotNull(response.getAddedList());
    }
}

