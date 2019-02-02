package lv.java2.shoping_list;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.sharedrules.EmptyStringRule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class EmptyStringRuleTest {

    private EmptyStringRule emptyStringRule = new EmptyStringRule();
    private List<ShoppingListError> errors;
    private String[] invalidString = {null, "", " ", "     "};
    private String[] validString = {" alala", " pooo ", "12345", "11 11 56"};

    @Before
    public void init() {
        this.errors = new ArrayList<>();
    }

    @Test
    public void returnErrorsIfStringIsEmpty() {
        Arrays.stream(invalidString).forEach(s -> emptyStringRule.execute(s, "string")
                .ifPresent(errors::add));
        assertFalse(errors.isEmpty());
        assertEquals(4, errors.size());
    }

    @Test
    public void returnNoErrorsIvStringIsNotEmpty() {
        Arrays.stream(validString).forEach(s -> emptyStringRule.execute(s, "string")
                .ifPresent(errors::add));
        assertTrue(errors.isEmpty());
    }
}

