package services.add.account.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.add.account.validation.AccountRegistrationValidator;
import lv.java2.shopping_list.services.add.account.validation.AccountRegistrationValidatorImpl;
import lv.java2.shopping_list.services.add.account.validation.rules.LoginRules;
import lv.java2.shopping_list.services.add.account.validation.rules.PasswordRules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountRegistrationValidatorTest {

    @Mock
    private LoginRules loginRules;

    @Mock
    private PasswordRules passwordRules;

    private AccountRegistrationRequest registrationRequest = new AccountRegistrationRequest("login@login.lv",
            "password1", "password1");

    @InjectMocks
    private AccountRegistrationValidator validator = new AccountRegistrationValidatorImpl();

    @Test
    public void collectAndReturnLoginErrors() {

        Mockito.when(loginRules.lessThan5Length(registrationRequest.getLogin()))
                .thenReturn(createError("login", "less than five rule"));
        Mockito.when(loginRules.doesNotContainLetters(registrationRequest.getLogin()))
                .thenReturn(createError("login", "letters rule"));
        Mockito.when(loginRules.emptyLogin(registrationRequest.getLogin()))
                .thenReturn(createError("login", "empty"));
        Mockito.when(loginRules.containsAtSignAndDotSign(registrationRequest.getLogin()))
                .thenReturn(createError("login", "@ and ."));
        Mockito.when(loginRules.duplicateLogin(registrationRequest.getLogin()))
                .thenReturn(createError("login", "duplicate"));

        List<ShoppingListError> errors = validator.validate(registrationRequest);
        assertEquals(5, errors.size());
    }

    @Test
    public void collectAndReturnPasswordErrors() {

        Mockito.when(passwordRules.containsDigits(registrationRequest.getMainPassword()))
                .thenReturn(createError("password", "digits"));
        Mockito.when(passwordRules.lessThan6Length(registrationRequest.getMainPassword()))
                .thenReturn(createError("password", "less than 6"));
        Mockito.when(passwordRules.matchPasswords(registrationRequest.getMainPassword(), registrationRequest.getPasswordToMatch()))
                .thenReturn(createError("password", "match"));

        List<ShoppingListError> errors = validator.validate(registrationRequest);
        assertEquals(3, errors.size());
    }

    private Optional<ShoppingListError> createError(String field, String description) {
        return Optional.of(new ShoppingListError(field, description));
    }
}
