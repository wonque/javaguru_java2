package services.account.registration.validation.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.account.registration.validation.AccountRegistrationValidator;
import lv.java2.shopping_list.services.account.registration.validation.AccountRegistrationValidatorImpl;
import lv.java2.shopping_list.services.account.registration.validation.rules.LoginRules;
import lv.java2.shopping_list.services.account.registration.validation.rules.PasswordRules;
import lv.java2.shopping_list.services.sharedrules.EmptyTitleSharedRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AccountRegistrationServiceValidatorTest {

    @Mock
    private LoginRules loginRules;

    @Mock
    private EmptyTitleSharedRule emptyTitleSharedRule;

    @Mock
    private PasswordRules passwordRules;

    private AccountRegistrationRequest registrationRequest = new AccountRegistrationRequest("login@login.lv",
            "password1");

    @InjectMocks
    private AccountRegistrationValidator validator = new AccountRegistrationValidatorImpl();

    @Test
    public void emptyLoginErrorTest() {
        Mockito.when(emptyTitleSharedRule.execute(registrationRequest.getLogin(), "login"))
                .thenReturn(createError("login", "null"));
        List<ShoppingListError> errors = validator.validate(registrationRequest);
        Mockito.verifyZeroInteractions(loginRules);
        Mockito.verifyZeroInteractions(passwordRules);
        assertEquals(1, errors.size());
    }

    @Test
    public void emptyPasswordErrorTest() {
        Mockito.when(emptyTitleSharedRule.execute(registrationRequest.getLogin(), "login"))
                .thenReturn(Optional.empty());
        Mockito.when(emptyTitleSharedRule.execute(registrationRequest.getPassword(), "password"))
                .thenReturn(createError("password", "null"));
        List<ShoppingListError> errors = validator.validate(registrationRequest);
        Mockito.verifyZeroInteractions(loginRules);
        Mockito.verifyZeroInteractions(passwordRules);
        assertEquals(1, errors.size());
    }


    @Test
    public void loginErrorsTestExceptEpmty() {
        Mockito.when(loginRules.lessThan5Length(registrationRequest.getLogin()))
                .thenReturn(createError("login", "less than five rule"));
        Mockito.when(loginRules.doesNotContainLetters(registrationRequest.getLogin()))
                .thenReturn(createError("login", "letters rule"));
        Mockito.when(loginRules.containsAtSignAndDotSign(registrationRequest.getLogin()))
                .thenReturn(createError("login", "@ and ."));
        Mockito.when(loginRules.duplicateLogin(registrationRequest.getLogin()))
                .thenReturn(createError("login", "duplicate"));

        List<ShoppingListError> errors = validator.validate(registrationRequest);
        assertEquals(4, errors.size());
    }

    @Test
    public void passwordErrorsTestExcemptEmpty() {
        Mockito.when(passwordRules.containsDigits(registrationRequest.getPassword()))
                .thenReturn(createError("password", "digits"));
        Mockito.when(passwordRules.lessThan6Length(registrationRequest.getPassword()))
                .thenReturn(createError("password", "less than 6"));

        List<ShoppingListError> errors = validator.validate(registrationRequest);
        assertEquals(2, errors.size());
    }

    @Test
    public void noErrorsIfLoginAndPassValid(){
        Mockito.when(emptyTitleSharedRule.execute(registrationRequest.getLogin(), "login"))
                .thenReturn(Optional.empty());
        Mockito.when(emptyTitleSharedRule.execute(registrationRequest.getPassword(), "password"))
                .thenReturn(Optional.empty());
        Mockito.when(loginRules.lessThan5Length(registrationRequest.getLogin()))
                .thenReturn(Optional.empty());
        Mockito.when(loginRules.doesNotContainLetters(registrationRequest.getLogin()))
                .thenReturn(Optional.empty());
        Mockito.when(loginRules.containsAtSignAndDotSign(registrationRequest.getLogin()))
                .thenReturn(Optional.empty());
        Mockito.when(loginRules.duplicateLogin(registrationRequest.getLogin()))
                .thenReturn(Optional.empty());
        Mockito.when(passwordRules.containsDigits(registrationRequest.getPassword()))
                .thenReturn(Optional.empty());
        Mockito.when(passwordRules.lessThan6Length(registrationRequest.getPassword()))
                .thenReturn(Optional.empty());
        List<ShoppingListError> errors = validator.validate(registrationRequest);
        assertTrue(errors.isEmpty());
    }

    private Optional<ShoppingListError> createError(String field, String description) {
        return Optional.of(new ShoppingListError(field, description));
    }
}
