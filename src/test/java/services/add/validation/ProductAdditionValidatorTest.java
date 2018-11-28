package services.add.validation;

import lv.java2.shopping_list.services.add.product.validation.ProductAdditionValidator;
import lv.java2.shopping_list.services.add.product.validation.ProductAdditionValidatorImpl;
import lv.java2.shopping_list.services.add.product.validation.rules.FirstCharacterRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.add.product.ProductAdditionRequest;
import lv.java2.shopping_list.services.add.product.validation.rules.DuplicateProductTitleRule;
import lv.java2.shopping_list.services.add.product.validation.rules.EmptyTitleRule;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ProductAdditionValidatorTest {

    @Mock
    private DuplicateProductTitleRule duplicateProductTitleRule;

    @Mock
    private EmptyTitleRule emptyTitleRule;

    @Mock
    private FirstCharacterRule firstCharacterRule;

    @InjectMocks
    private ProductAdditionValidator validator = new ProductAdditionValidatorImpl();

    @Test
    public void collectAndReturnErrorsIfAllRulesFailed() {
        Mockito.when(emptyTitleRule.execute("milk"))
                .thenReturn(Optional.of(new Error("title", "empty")));
        Mockito.when(firstCharacterRule.execute("milk"))
                .thenReturn(Optional.of(new Error("title", "first char")));
        Mockito.when(duplicateProductTitleRule.execute("milk"))
                .thenReturn(Optional.of(new Error("title", "duplicate")));

        List<Error> errors = validator.validate(new ProductAdditionRequest("milk"));

        assertEquals(3, errors.size());
    }

}
