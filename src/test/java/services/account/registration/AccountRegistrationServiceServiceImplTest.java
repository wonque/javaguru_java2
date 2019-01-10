package services.account.registration;


import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.account.Account;

import lv.java2.shopping_list.domain.account.AccountFactory;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.account.AccountPasswordHashService;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationResponse;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationServiceImpl;
import lv.java2.shopping_list.services.account.registration.validation.AccountRegistrationValidator;
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
public class AccountRegistrationServiceServiceImplTest {

    @Mock
    private AccountRepository repository;
    @Mock
    private AccountFactory itemBuilder;
    @Mock
    private AccountRegistrationValidator validator;
    @Mock
    private AccountPasswordHashService passwordHashService;

    private AccountRegistrationRequest registrationRequest;
    private List<ShoppingListError> errors;

    @InjectMocks
    private AccountRegistrationServiceImpl registrationService = new AccountRegistrationServiceImpl();

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

        Mockito.when(passwordHashService.hashPassword(registrationRequest.getPassword()))
                .thenReturn("hashedPassword");

        Mockito.when(itemBuilder.buildInstance(registrationRequest.getLogin(),
                "hashedPassword",
                registrationRequest.getUserName()))
                .thenReturn(account);

        Mockito.when(repository.addToBase(account)).thenReturn(account);

        AccountRegistrationResponse response = registrationService.register(registrationRequest);
        assertTrue(response.isSuccess());
        assertNotNull(response.getAccount());
    }
}


