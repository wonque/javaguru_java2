package services.add.shoppinglist;

import lv.java2.shopping_list.db.ShoppingListRepository;

import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.builders.ShoppingListBuilder;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.add.account.registration.AccountRegistrationResponse;
import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionRequest;
import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionResponse;
import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionService;
import lv.java2.shopping_list.services.add.shoppinglist.validation.ShoppingListAdditionValidator;
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
    private ShoppingListBuilder itemBuilder;

    private ShoppingListAdditionRequest request;
    private List<ShoppingListError> errors;
    private Account account;

    @Before
    public void buildRequestAndErrors() {
        this.account = new Account();
        account.setLogin("login");
        account.setPassword("password");
        this.request = new ShoppingListAdditionRequest(account, "testList");
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

