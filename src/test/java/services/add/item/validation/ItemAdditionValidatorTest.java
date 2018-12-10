package services.add.item.validation;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.item.ItemAdditionRequest;
import lv.java2.shopping_list.services.add.item.validation.ItemAdditionValidator;
import lv.java2.shopping_list.services.add.item.validation.ItemAdditionValidatorImpl;
import lv.java2.shopping_list.services.add.item.validation.ItemAdditionRules;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ItemAdditionValidatorTest {

    private ShoppingList shoppingList;
    private ItemAdditionRequest itemAddRequest;

    @Mock
    private ItemAdditionRules itemAdditionRules;

    @InjectMocks
    private ItemAdditionValidator validator = new ItemAdditionValidatorImpl();

    @Before
    public void initShoppingListAndRequest() {
        this.shoppingList = new ShoppingList();
        this.itemAddRequest = new ItemAdditionRequest(shoppingList, "milk");
    }

    @Test
    public void collectAndReturnErrorsIfAllRulesFailed() {

        Mockito.when(itemAdditionRules.emptyTitle("milk")).thenReturn(createError("title", "empty"));
        Mockito.when(itemAdditionRules.duplicateItem(shoppingList, "milk"))
                .thenReturn(createError("title", "duplicate"));

        List<ShoppingListError> errors = validator.validate(itemAddRequest);
        assertEquals(2, errors.size());
    }

    private Optional<ShoppingListError> createError(String field, String description) {
        return Optional.of(new ShoppingListError(field, description));
    }
}
