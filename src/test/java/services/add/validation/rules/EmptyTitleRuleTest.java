package services.add.validation.rules;

import org.junit.Test;
import services.Error;
import services.add.validation.rules.EmptyTitleRule;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class EmptyTitleRuleTest {

    private EmptyTitleRule rule = new EmptyTitleRule();

    @Test
    public void returnErrorIfTitleIsNull() {
        Optional<Error> error = rule.execute(null);
        assertTrue(error.isPresent());
    }

    @Test
    public void returnErrorIfTitleIsEmptyString() {
        Optional<Error> error = rule.execute("");
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("Empty title not allowed!", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfTitleIsWhitespaceCharacters() {
        Optional<Error> error = rule.execute("      ");
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("Empty title not allowed!", error.get().getErrorDescription());
    }

    @Test
    public void returnNoErrorIfTitleValid() {
        Optional<Error> error = rule.execute("tomato");
        assertFalse(error.isPresent());
    }


}
