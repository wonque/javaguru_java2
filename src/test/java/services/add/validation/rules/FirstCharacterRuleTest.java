package services.add.validation.rules;

import lv.java2.shopping_list.services.add.product.validation.rules.FirstCharacterRule;
import org.junit.Test;
import lv.java2.shopping_list.services.Error;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class FirstCharacterRuleTest {

    private FirstCharacterRule rule = new FirstCharacterRule();

    @Test
    public void returnErrorWhenFirstCharacterNotALetter() {
        Optional<Error> error = rule.execute("*title");
        assertTrue(error.isPresent());
    }

    @Test
    public void returnNoErrorIfFirstCharacterIsWordCharacter() {
        Optional<Error> error = rule.execute("6title");
        assertFalse(error.isPresent());
    }

}
