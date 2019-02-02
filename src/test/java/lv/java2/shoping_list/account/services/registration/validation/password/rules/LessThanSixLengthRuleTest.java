package lv.java2.shoping_list.account.services.registration.validation.password.rules;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.registration.validation.password.rules.LessThanSixLengthRule;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.*;

public class LessThanSixLengthRuleTest {

    private LessThanSixLengthRule lessThanSixLengthRule = new LessThanSixLengthRule();


    @Test
    public void errorIfPasswordLengthLessThan6() {
        Optional<ShoppingListError> error = lessThanSixLengthRule.apply("pass");
        assertTrue(error.isPresent());
        assertEquals("password", error.get().getField());
        assertEquals("Password too short! Minimum length is 6 symbols", error.get().getErrorDescription());

    }

    @Test
    public void returnNoErrorIfPasswordLengthMoreThan6() {
        Optional<ShoppingListError> error = lessThanSixLengthRule.apply("password123");
        assertFalse(error.isPresent());

    }
}
