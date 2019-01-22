package account.services.password.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.password.validation.PasswordSpellingValidatorImpl;
import lv.java2.shopping_list.account.services.password.validation.rules.ContainsThreeDigitsRule;
import lv.java2.shopping_list.account.services.password.validation.rules.LessThanSixLengthRule;
import lv.java2.shopping_list.sharedrules.EmptyStringRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PasswordSpellingValidatorImplTest {

    @Mock
    private EmptyStringRule emptyStringRule;

    @Mock
    private ContainsThreeDigitsRule containsThreeDigitsRule;

    @Mock
    private LessThanSixLengthRule lessThanSixLengthRule;


    @InjectMocks
    private PasswordSpellingValidatorImpl spellingValidator = new PasswordSpellingValidatorImpl();

    @Test
    public void errorIfPasswordIsEmptyString() {
        Mockito.when(emptyStringRule.execute("string", "password"))
                .thenReturn(Optional.of(new ShoppingListError("field", "empty")));
        List<ShoppingListError> errors = spellingValidator.validate("string");
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        Mockito.verifyZeroInteractions(containsThreeDigitsRule);
        Mockito.verifyZeroInteractions(lessThanSixLengthRule);
    }

    @Test
    public void errorIfPasswordNotContains3digits() {
        Mockito.when(emptyStringRule.execute("string", "password"))
                .thenReturn(Optional.empty());
        Mockito.when(containsThreeDigitsRule.execute("string"))
                .thenReturn(Optional.of(new ShoppingListError("field", "3digits")));
        Mockito.when(lessThanSixLengthRule.execute("string"))
                .thenReturn(Optional.empty());
        List<ShoppingListError> errors = spellingValidator.validate("string");
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
    }

    @Test
    public void noErrorsIfPasswordSpelledCorrect() {
        Mockito.when(emptyStringRule.execute("string", "password"))
                .thenReturn(Optional.empty());
        Mockito.when(containsThreeDigitsRule.execute("string"))
                .thenReturn(Optional.empty());
        Mockito.when(lessThanSixLengthRule.execute("string"))
                .thenReturn(Optional.empty());
        List<ShoppingListError> errors = spellingValidator.validate("string");
        assertTrue(errors.isEmpty());
    }
}
