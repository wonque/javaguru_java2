package lv.java2.shoping_list.account.services.registration.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.registration.validation.password.PasswordSpellingValidator;
import lv.java2.shopping_list.account.services.registration.validation.login.LoginSpellingValidator;
import lv.java2.shopping_list.account.services.registration.validation.AccountRegistrationValidator;
import lv.java2.shopping_list.account.services.registration.validation.AccountRegistrationValidatorImpl;
import lv.java2.shopping_list.account.services.registration.validation.DuplicateAccountRule;
import lv.java2.shopping_list.web.dto.AccountDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountRegistrationValidatorTest {

    @Mock
    private LoginSpellingValidator loginSpellingValidator;

    @Mock
    private PasswordSpellingValidator passwordSpellingValidator;

    @Mock
    private DuplicateAccountRule duplicateAccountRule;

    private AccountDTO accountDTO;
    private List<ShoppingListError> loginErrors;
    private List<ShoppingListError> passwordErrors;

    @Before
    public void initRequest() {
        this.accountDTO = new AccountDTO("login@login.lv",
                "password1", "vasya");
        this.loginErrors = new ArrayList<>();
        this.passwordErrors = new ArrayList<>();

    }

    @InjectMocks
    public AccountRegistrationValidator validator = new AccountRegistrationValidatorImpl();

    @Test
    public void returnErrorsIfLoginAndPasswordNotValid() {
        loginErrors.add(new ShoppingListError("login", "error"));
        passwordErrors.add(new ShoppingListError("password", "error"));
        Mockito.when(loginSpellingValidator.validate(accountDTO.getEmail())).thenReturn(loginErrors);
        Mockito.when(passwordSpellingValidator.validate(accountDTO.getPassword())).thenReturn(passwordErrors);
        List<ShoppingListError> errors = validator.validate(accountDTO);
        assertFalse(errors.isEmpty());
        assertEquals(2, errors.size());
        Mockito.verifyZeroInteractions(duplicateAccountRule);
    }


    @Test
    public void returnErrorIfDuplicateAccount() {
        Mockito.when(loginSpellingValidator.validate(accountDTO.getEmail())).thenReturn(loginErrors);
        Mockito.when(passwordSpellingValidator.validate(accountDTO.getPassword())).thenReturn(passwordErrors);
        Mockito.when(duplicateAccountRule.apply(accountDTO.getEmail()))
                .thenReturn(Optional.of(new ShoppingListError("login", "duplicate")));
        List<ShoppingListError> errors = validator.validate(accountDTO);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
    }

    @Test
    public void noErrorsIfLoginAndPassValid() {
        Mockito.when(loginSpellingValidator.validate(accountDTO.getEmail()))
                .thenReturn(loginErrors);
        Mockito.when(passwordSpellingValidator.validate(accountDTO.getPassword()))
                .thenReturn(passwordErrors);
        Mockito.when(duplicateAccountRule.apply(accountDTO.getEmail())).thenReturn(Optional.empty());
        List<ShoppingListError> errors = validator.validate(accountDTO);

        Mockito.verify(loginSpellingValidator, Mockito.times(1))
                .validate(accountDTO.getEmail());
        Mockito.verify(passwordSpellingValidator, Mockito.times(1))
                .validate(accountDTO.getPassword());
        Mockito.verify(duplicateAccountRule, Mockito.times(1)).apply(accountDTO.getEmail());
        assertTrue(errors.isEmpty());

    }

}
