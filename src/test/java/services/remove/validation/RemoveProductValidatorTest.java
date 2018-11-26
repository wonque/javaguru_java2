package services.remove.validation;

import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.remove.RemoveProductRequest;
import lv.java2.shopping_list.services.remove.RemoveProductValidatorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import lv.java2.shopping_list.services.remove.RemoveProductRules;
import lv.java2.shopping_list.services.remove.RemoveProductValidator;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductValidatorTest {

    @Mock
    private RemoveProductRules rules;

    @InjectMocks
    private RemoveProductValidator validator = new RemoveProductValidatorImpl();


    @Test
    public void collectAndReturnErrorsIfAllRulesFailed() {
        Mockito.when(rules.nullTitleRule("milk"))
                .thenReturn(Optional.of(new Error("title", "NUll title")));
        Mockito.when(rules.productPresenceInDataBaseRule("milk"))
                .thenReturn(Optional.of(new Error("title", "No product found")));

        List<Error> errors = validator.validate(new RemoveProductRequest("milk"));
        assertEquals(2, errors.size());
    }
}
