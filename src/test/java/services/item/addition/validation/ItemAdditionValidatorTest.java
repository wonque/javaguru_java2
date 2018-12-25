package services.item.addition.validation;

import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.addition.validation.ItemAdditionValidator;
import lv.java2.shopping_list.services.item.addition.validation.ItemAdditionValidatorImpl;
import lv.java2.shopping_list.services.item.addition.validation.rules.DuplicateItemRule;
import lv.java2.shopping_list.services.item.ItemAddRemoveSharedRequest;
import lv.java2.shopping_list.services.sharedrules.EmptyTitleSharedRule;
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
    private EmptyTitleSharedRule titleSharedRule;

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
