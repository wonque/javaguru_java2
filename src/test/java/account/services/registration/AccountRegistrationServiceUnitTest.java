package account.services.registration;


import lv.java2.shopping_list.account.repository.AccountRepository;
import lv.java2.shopping_list.account.domain.Account;

import lv.java2.shopping_list.account.domain.AccountFactory;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.password.PasswordService;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationResponse;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationServiceImpl;
import lv.java2.shopping_list.account.services.registration.validation.AccountRegistrationValidator;
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
public class AccountRegistrationServiceUnitTest {

    @Mock
    private AccountRepository repository;
    @Mock
    private AccountFactory accountFactory;
    @Mock
    private AccountRegistrationValidator validator;
    @Mock
    private PasswordService passwordService;

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

        Mockito.when(passwordService.processPassword(registrationRequest.getPassword()))
                .thenReturn("hashedPassword");

        Mockito.when(accountFactory.buildInstance(registrationRequest.getLogin(),
                "hashedPassword",
                registrationRequest.getUserName()))
                .thenReturn(account);

        Mockito.when(repository.save(account)).thenReturn(account);

        AccountRegistrationResponse response = registrationService.register(registrationRequest);
        assertTrue(response.isSuccess());
        assertNotNull(response.getAccount());
    }
}


