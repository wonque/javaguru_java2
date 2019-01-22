package account.services.registration.validation.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.password.validation.PasswordSpellingValidator;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.account.services.registration.login.LoginSpellingValidator;
import lv.java2.shopping_list.account.services.registration.validation.AccountRegistrationValidator;
import lv.java2.shopping_list.account.services.registration.validation.AccountRegistrationValidatorImpl;
import lv.java2.shopping_list.account.services.registration.login.rules.LoginRules;
import lv.java2.shopping_list.account.services.registration.validation.rules.PasswordRules;
import lv.java2.shopping_list.sharedrules.EmptyStringRule;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

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

    private AccountRegistrationRequest registrationRequest;
    private List<ShoppingListError> loginErrors;
    private List<ShoppingListError> passwordErrors;

    @Before
    public void initRequest() {
        this.registrationRequest = new AccountRegistrationRequest("login@login.lv",
                "password1");
        this.loginErrors = new ArrayList<>();
        this.passwordErrors = new ArrayList<>();

    }

    @InjectMocks
    public AccountRegistrationValidator validator = new AccountRegistrationValidatorImpl();

    @Test
    public void returnErrorsIfLoginNotValid() {
        loginErrors.add(new ShoppingListError("login", "error"));
        Mockito.when(loginSpellingValidator.validate(registrationRequest.getLogin()))
                .thenReturn(loginErrors);
        List<ShoppingListError> errors = validator.validate(registrationRequest);
        assertFalse(errors.isEmpty());
        Mockito.verifyZeroInteractions(passwordSpellingValidator);
    }

    @Test
    public void returnErrorsIfLoginValidPasswordNot() {
        passwordErrors.add(new ShoppingListError("password", "error"));
        Mockito.when(loginSpellingValidator.validate(registrationRequest.getLogin()))
                .thenReturn(loginErrors);
        Mockito.when(passwordSpellingValidator.validate(registrationRequest.getPassword()))
                .thenReturn(passwordErrors);
        List<ShoppingListError> errors = validator.validate(registrationRequest);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
    }

    @Test
    public void noErrorsIfLoginAndPassValid() {
        Mockito.when(loginSpellingValidator.validate(registrationRequest.getLogin()))
                .thenReturn(loginErrors);
        Mockito.when(passwordSpellingValidator.validate(registrationRequest.getPassword()))
                .thenReturn(passwordErrors);
        List<ShoppingListError> errors = validator.validate(registrationRequest);

        Mockito.verify(loginSpellingValidator, Mockito.times(1))
                .validate(registrationRequest.getLogin());
        Mockito.verify(passwordSpellingValidator, Mockito.times(1))
                .validate(registrationRequest.getPassword());
        assertTrue(errors.isEmpty());

    }

}
