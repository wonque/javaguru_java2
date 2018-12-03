package services.add.account.validation.rules;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.account.validation.rules.LoginRules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LoginRulesTest {

    @Mock
    private AccountRepository accountRepository;


    @InjectMocks
    private LoginRules loginRules;

    @Test
    public void returnErrorIfLoginEmpty() {
        Optional<ShoppingListError> error = loginRules.emptyLogin("");
        assertTrue(error.isPresent());
        assertEquals("login", error.get().getField());
        assertEquals("Empty login not allowed!", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfLoginIsNull() {
        Optional<ShoppingListError> error = loginRules.emptyLogin(null);
        assertTrue(error.isPresent());
        assertEquals("login", error.get().getField());
        assertEquals("Empty login not allowed!", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfLoginIsWhitespace() {
        Optional<ShoppingListError> error = loginRules.emptyLogin("   ");
        assertTrue(error.isPresent());
        assertEquals("login", error.get().getField());
        assertEquals("Empty login not allowed!", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfLoginLengthLessThan5() {
        Optional<ShoppingListError> error = loginRules.lessThan5Length("1234");
        assertTrue(error.isPresent());
        assertEquals("login", error.get().getField());
        assertEquals("Login too short! Minimum length is 5 symbols", error.get().getErrorDescription());

    }

    @Test
    public void returnErrorIfLoginDoesNotContainAtAndDot() {
        Optional<ShoppingListError> error = loginRules.containsAtSignAndDotSign("12345");
        assertTrue(error.isPresent());
        assertEquals("login", error.get().getField());
        assertEquals("Login is not an email address!", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfLoginContainsOnlyAtSign() {
        Optional<ShoppingListError> error = loginRules.containsAtSignAndDotSign("12@45");
        assertTrue(error.isPresent());
        assertEquals("login", error.get().getField());
        assertEquals("Login is not an email address!", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfLoginContainsOnyDot() {
        Optional<ShoppingListError> error = loginRules.containsAtSignAndDotSign("12.45");
        assertTrue(error.isPresent());
        assertEquals("login", error.get().getField());
        assertEquals("Login is not an email address!", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfLoginDoesNotContainLetters() {
        Optional<ShoppingListError> error = loginRules.doesNotContainLetters("12@4.5");
        assertTrue(error.isPresent());
        assertEquals("login", error.get().getField());
        assertEquals("Login must contain letters and numbers", error.get().getErrorDescription());
    }

    @Test
    public void returnNoErrorIfLoginContainsLetters() {
        Optional<ShoppingListError> error = loginRules.doesNotContainLetters("12@4a5");
        assertFalse(error.isPresent());
    }

    @Test
    public void returnErrorIfLoginInDatabase() {
        Account account = new Account("login@one.ku", "password");
        Mockito.when(accountRepository.findAccountByLogin("login@one.ku")).thenReturn(Optional.of(account));
        Optional<ShoppingListError> error = loginRules.duplicateLogin("login@one.ku");
        assertTrue(error.isPresent());
        assertEquals("login", error.get().getField());
        assertEquals("Account login@one.ku already registered!", error.get().getErrorDescription());
    }
}
