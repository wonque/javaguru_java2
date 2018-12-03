package services.add.account.validation.rules;


import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.account.validation.rules.PasswordRules;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class PasswordRulesTest {

    private PasswordRules passwordRules = new PasswordRules();


    @Test
    public void returnErrorIsPasswordLengthLessThan6() {
        Optional<ShoppingListError> error = passwordRules.lessThan6Length("pass");
        assertTrue(error.isPresent());
        assertEquals("password", error.get().getField());
        assertEquals("Password too short! Minimum length is 6 symbols", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfPasswordNotContainDigits() {
        Optional<ShoppingListError> error = passwordRules.containsDigits("password");
        assertTrue(error.isPresent());
        assertEquals("password", error.get().getField());
        assertEquals("Password must contain at least one digit", error.get().getErrorDescription());

    }

    @Test
    public void returnErrorIfPasswordsNotEquals() {
        Optional<ShoppingListError> error = passwordRules.matchPasswords("pass1", "pass2");
        assertTrue(error.isPresent());
        assertEquals("password", error.get().getField());
        assertEquals("Entered password and confirmation password must match!", error.get().getErrorDescription());
    }

    @Test
    public void returnTrueIfPasswordsAreEquals() {
        Optional<ShoppingListError> error = passwordRules.matchPasswords("pass1", "pass1");
        assertFalse(error.isPresent());
    }
}
