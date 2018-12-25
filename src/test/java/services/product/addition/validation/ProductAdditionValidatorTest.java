package services.product.addition.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.product.adition.validation.ProductAdditionValidator;
import lv.java2.shopping_list.services.product.adition.validation.ProductAdditionValidatorImpl;
import lv.java2.shopping_list.services.product.adition.validation.rules.FirstCharacterRule;
import lv.java2.shopping_list.services.sharedrules.EmptyTitleSharedRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import lv.java2.shopping_list.services.product.adition.ProductAdditionRequest;
import lv.java2.shopping_list.services.product.adition.validation.rules.DuplicateProductTitleRule;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductAdditionValidatorTest {

    @Mock
    private DuplicateProductTitleRule duplicateProductTitleRule;

    @Mock
    private EmptyTitleSharedRule emptyTitleRule;

    @Mock
    private FirstCharacterRule firstCharacterRule;

    @InjectMocks
    private ProductAdditionValidator validator = new ProductAdditionValidatorImpl();

    @Test
    public void collectAndReturnErrorsIfAllRulesFailed() {
        Mockito.when(emptyTitleRule.execute("milk", "title"))
                .thenReturn(Optional.of(new ShoppingListError("title", "empty")));
        Mockito.when(firstCharacterRule.execute("milk"))
                .thenReturn(Optional.of(new ShoppingListError("title", "first char")));
        Mockito.when(duplicateProductTitleRule.execute("milk"))
                .thenReturn(Optional.of(new ShoppingListError("title", "duplicate")));

        List<ShoppingListError> errors = validator.validate(new ProductAdditionRequest("milk"));

        assertEquals(3, errors.size());
    }

}
