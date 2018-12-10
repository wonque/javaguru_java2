package services.add.shoppinglist.validation;

import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionRequest;
import lv.java2.shopping_list.services.add.shoppinglist.validation.ShoppingListAdditionValidator;
import lv.java2.shopping_list.services.add.shoppinglist.validation.ShoppingListAdditionValidatorImpl;
import lv.java2.shopping_list.services.add.shoppinglist.validation.ShoppingListValidationRules;
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
public class ShoppingListAdditionValidatorTest {

    @Mock
    private ShoppingListValidationRules validationRules;

    @InjectMocks
    private ShoppingListAdditionValidator validator = new ShoppingListAdditionValidatorImpl();


    @Test
    public void collectAndReturnErrorsIfAllRulesFailed() {
        Account account = Mockito.mock(Account.class);
        ShoppingListAdditionRequest request = new ShoppingListAdditionRequest(account, "title");

        Mockito.when(validationRules.emptyTitleRule("title"))
                .thenReturn(createError("title", "empty"));

        Mockito.when(validationRules.duplicateEntryRule(account, "title"))
                .thenReturn(createError("title", "duplicate"));

        List<ShoppingListError> errors = validator.validate(request);
        assertEquals(2, errors.size());
    }

    private Optional<ShoppingListError> createError(String field, String description) {
        return Optional.of(new ShoppingListError(field, description));
    }
}
