package item.services.addition.validation;

import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.item.services.addition.validation.ItemAdditionValidator;
import lv.java2.shopping_list.item.services.addition.validation.ItemAdditionValidatorImpl;
import lv.java2.shopping_list.item.services.addition.validation.rules.DuplicateItemRule;
import lv.java2.shopping_list.item.services.ItemAddRemoveSharedRequest;
import lv.java2.shopping_list.sharedrules.EmptyStringRule;
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
    private ItemAddRemoveSharedRequest itemAddRequest;

    @Mock
    private DuplicateItemRule duplicateItemRule;
    @Mock
    private EmptyStringRule titleSharedRule;

    @InjectMocks
    private ItemAdditionValidator validator = new ItemAdditionValidatorImpl();

    @Before
    public void initShoppingListAndRequest() {
        this.shoppingList = new ShoppingList();
        this.itemAddRequest = new ItemAddRemoveSharedRequest(shoppingList, "milk");
    }

    @Test
    public void collectAndReturnErrorsIfAllRulesFailed() {

        Mockito.when(titleSharedRule.execute("milk", "title"))
                .thenReturn(createError("title", "empty"));
        Mockito.when(duplicateItemRule.execute(shoppingList, "milk"))
                .thenReturn(createError("title", "duplicate"));

        List<ShoppingListError> errors = validator.validate(itemAddRequest);
        assertEquals(2, errors.size());
    }

    private Optional<ShoppingListError> createError(String field, String description) {
        return Optional.of(new ShoppingListError(field, description));
    }
}
