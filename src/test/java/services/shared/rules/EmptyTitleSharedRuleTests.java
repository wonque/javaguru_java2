package services.shared.rules;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.EmptyTitleSharedRule;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class EmptyTitleSharedRuleTests {

    private EmptyTitleSharedRule titleSharedRule = new EmptyTitleSharedRule();

    @Test
    public void returnErrorIfLoginEmpty() {
        Optional<ShoppingListError> error = titleSharedRule.execute("", "title");
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("This field cannot be empty", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfLoginIsNull() {
        Optional<ShoppingListError> error = titleSharedRule.execute(null, "title");
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("This field cannot be empty", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfLoginIsWhitespace() {
        Optional<ShoppingListError> error = titleSharedRule.execute("   ", "title");
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("This field cannot be empty", error.get().getErrorDescription());
    }
}
