package lv.java2.shoping_list.account.services.registration;


import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.account.repository.AccountRepository;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.password.PasswordService;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationResponse;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationServiceImpl;
import lv.java2.shopping_list.account.services.registration.validation.AccountRegistrationValidator;
import lv.java2.shopping_list.web.dto.AccountDTO;
import lv.java2.shopping_list.web.dto.mappers.AccountDTOMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.class)
public class AccountRegistrationServiceUnitTest {

    @Mock
    private AccountRepository repository;
    @Mock
    private AccountRegistrationValidator validator;
    @Mock
    private AccountDTOMapper accountDTOMapper;

    private AccountDTO accountDTO;
    private List<ShoppingListError> errors;

    @InjectMocks
    private AccountRegistrationServiceImpl registrationService = new AccountRegistrationServiceImpl();

    @Before
    public void init() {
        this.accountDTO = new AccountDTO("login", "password1", "vasya");
        this.errors = new ArrayList<>();
    }


    @Test
    public void returnResponseWithListOfErrors() {
        errors.add(new ShoppingListError("title", "description"));
        Mockito.when(validator.validate(accountDTO)).thenReturn(errors);
        AccountRegistrationResponse response = registrationService.register(accountDTO);
        assertEquals(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
        assertEquals(1, response.getErrors().size());
        Mockito.verifyZeroInteractions(repository);
    }

    @Test
    public void returnResponseWithRegisteredAccount() {
        Mockito.when(validator.validate(accountDTO)).thenReturn(errors);
        AccountRegistrationResponse response = registrationService.register(accountDTO);
        assertEquals(response.getHttpStatus(), HttpStatus.CREATED);
        assertNotNull(response.getAccountDTO());
        assertNull(response.getAccountDTO().getId());
        System.out.println(response.getAccountDTO());
    }
}


