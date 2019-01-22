package account.services.password.validation.rules;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.password.validation.rules.ContainsThreeDigitsRule;
import org.junit.Test;


import java.util.Optional;

import static junit.framework.TestCase.*;

public class ContainsThreeDigitsRuleTest {

    private ContainsThreeDigitsRule containsThreeDigitsRule = new ContainsThreeDigitsRule();

    @Test
    public void errorIfPasswordDoesNotContain3digits() {
        Optional<ShoppingListError> error = containsThreeDigitsRule.execute("password12");
        assertTrue(error.isPresent());
        assertEquals("password", error.get().getField());
        assertEquals("Password must contain at least three digits", error.get().getErrorDescription());

    }

    @Test
    public void returnNoErrorIfPasswordContains3orMoreDigits() {
        Optional<ShoppingListError> threeDigitsError = containsThreeDigitsRule.execute("password123");
        assertFalse(threeDigitsError.isPresent());

    }
}
