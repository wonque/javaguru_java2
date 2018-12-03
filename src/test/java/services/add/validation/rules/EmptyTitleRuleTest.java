package services.add.validation.rules;

import lv.java2.shopping_list.services.ShoppingListError;
import org.junit.Test;
import lv.java2.shopping_list.services.add.product.validation.rules.EmptyTitleRule;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class EmptyTitleRuleTest {

    private EmptyTitleRule rule = new EmptyTitleRule();

    @Test
    public void returnErrorIfTitleIsNull() {
        Optional<ShoppingListError> error = rule.execute(null);
        assertTrue(error.isPresent());
    }

    @Test
    public void returnErrorIfTitleIsEmptyString() {
        Optional<ShoppingListError> error = rule.execute("");
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("Empty title not allowed!", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfTitleIsWhitespaceCharacters() {
        Optional<ShoppingListError> error = rule.execute("      ");
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("Empty title not allowed!", error.get().getErrorDescription());
    }

    @Test
    public void returnNoErrorIfTitleValid() {
        Optional<ShoppingListError> error = rule.execute("tomato");
        assertFalse(error.isPresent());
    }


}
