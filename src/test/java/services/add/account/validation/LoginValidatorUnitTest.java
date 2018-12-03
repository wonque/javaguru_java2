package services.add.account.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.account.validation.login.LoginValidationRequest;
import lv.java2.shopping_list.services.add.account.validation.login.LoginValidationResponse;
import lv.java2.shopping_list.services.add.account.validation.login.LoginValidator;
import lv.java2.shopping_list.services.add.account.validation.rules.LoginRules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;


@RunWith(MockitoJUnitRunner.class)
public class LoginValidatorUnitTest {

    @Mock
    private LoginRules loginRules;

    @InjectMocks
    private LoginValidator validator = new LoginValidator(loginRules);


    @Test
    public void collectAndReturnErrorsIfAllRulesFailed() {
        Mockito.when(loginRules.containsAtSignAndDotSign("login"))
                .thenReturn(Optional.of(new ShoppingListError("login", "at and dor")));
        Mockito.when(loginRules.emptyLogin("login"))
                .thenReturn(Optional.of(new ShoppingListError("login", "empty")));
        Mockito.when(loginRules.doesNotContainLetters("login"))
                .thenReturn(Optional.of(new ShoppingListError("login", "description")));
        Mockito.when(loginRules.duplicateLogin("login"))
                .thenReturn(Optional.of(new ShoppingListError("login", "description")));
        Mockito.when(loginRules.lessThan5Length("login"))
                .thenReturn(Optional.of(new ShoppingListError("login", "description")));

        LoginValidationRequest request = new LoginValidationRequest("login");
        LoginValidationResponse response = validator.validateLogin("login");
        assertEquals(5, response.getErrors().size());
        assertFalse(response.isSuccess());
    }

}
