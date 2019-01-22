package account.services.password.validation.rules;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.password.validation.rules.LessThanSixLengthRule;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.*;

public class LessThanSixLengthRuleTest {

    private LessThanSixLengthRule lessThanSixLengthRule = new LessThanSixLengthRule();


    @Test
    public void errorIfPasswordLengthLessThan6() {
        Optional<ShoppingListError> error = lessThanSixLengthRule.execute("pass");
        assertTrue(error.isPresent());
        assertEquals("password", error.get().getField());
        assertEquals("Password too short! Minimum length is 6 symbols", error.get().getErrorDescription());

    }

    @Test
    public void returnNoErrorIfPasswordLengthMoreThan6() {
        Optional<ShoppingListError> error = lessThanSixLengthRule.execute("password123");
        assertFalse(error.isPresent());

    }
}
