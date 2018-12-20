package services.account.registration;


import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.Account;

import lv.java2.shopping_list.domain.factories.AccountFactory;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationResponse;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationService;
import lv.java2.shopping_list.services.account.validation.AccountRegistrationValidator;
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
public class AccountRegistrationServiceTest {

    @Mock
    private AccountRepository repository;
    @Mock
    private AccountFactory itemBuilder;
    @Mock
    private AccountRegistrationValidator validator;

    private AccountRegistrationRequest registrationRequest;
    private List<ShoppingListError> errors;

    @InjectMocks
    private AccountRegistrationService registrationService = new AccountRegistrationService();

    @Before
    public void buildRequestAndErrors() {
        this.registrationRequest = new AccountRegistrationRequest("login", "password1");
        this.errors = new ArrayList<>();
    }


    @Test
    public void shouldReturnResponseWithListOfErrors() {
        errors.add(new ShoppingListError("title", "description"));
        Mockito.when(validator.validate(registrationRequest)).thenReturn(errors);
        AccountRegistrationResponse response = registrationService.register(registrationRequest);
        assertFalse(response.isSuccess());
        assertEquals(1, response.getErrors().size());
        Mockito.verifyZeroInteractions(repository);
    }

    @Test
    public void shouldReturnResponseWithAccount() {
        Account account = Mockito.mock(Account.class);
        Mockito.when(validator.validate(registrationRequest)).thenReturn(errors);
        Mockito.when(itemBuilder.buildInstance(registrationRequest.getLogin(), registrationRequest.getMainPassword(),
                registrationRequest.getUserName())).thenReturn(account);
        Mockito.when(repository.addToBase(account)).thenReturn(account);
        AccountRegistrationResponse response = registrationService.register(registrationRequest);
        assertTrue(response.isSuccess());
        assertNotNull(response.getAccount());
    }


}


