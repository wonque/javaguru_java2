package services.shoppinglist.addition.validation;

import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListSharedRequest;
import lv.java2.shopping_list.services.shoppinglist.addition.validation.ShoppingListAdditionValidator;
import lv.java2.shopping_list.services.shoppinglist.addition.validation.ShoppingListAdditionValidatorImpl;
import lv.java2.shopping_list.services.shoppinglist.addition.validation.ShoppingListValidationRules;
import lv.java2.shopping_list.services.sharedrules.EmptyTitleSharedRule;
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
    @Mock
    private EmptyTitleSharedRule emptyTitleSharedRule;

    @InjectMocks
    private ShoppingListAdditionValidator validator = new ShoppingListAdditionValidatorImpl();


    @Test
    public void collectAndReturnErrorsIfAllRulesFailed() {
        Account account = Mockito.mock(Account.class);
        ShoppingListSharedRequest request = new ShoppingListSharedRequest(account, "title");

        Mockito.when(validationRules.duplicateEntryRule(account, "title"))
                .thenReturn(createError("title", "duplicate"));

        List<ShoppingListError> errors = validator.validate(request);
        assertEquals(1, errors.size());
    }

    private Optional<ShoppingListError> createError(String field, String description) {
        return Optional.of(new ShoppingListError(field, description));
    }
}
