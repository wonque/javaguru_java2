package services.add.shoppinglist.validation;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.shoppinglist.validation.ShoppingListValidationRules;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListValidationRulesTest {


    @Mock
    private ShoppingListRepository repository;

    @InjectMocks
    private ShoppingListValidationRules validationRules;

    private Account account;
    private ShoppingList shoppingList;

    @Before
    public void init() {
        this.account = Mockito.mock(Account.class);
        this.shoppingList = new ShoppingList();
        shoppingList.setTitle("list1");
    }

    @Test
    public void returnErrorIfTitleIsEmpty() {
        Optional<ShoppingListError> error = validationRules.emptyTitleRule("");
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("Empty title not allowed!", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfTitleIsWhitespace() {
        Optional<ShoppingListError> error = validationRules.emptyTitleRule("   ");
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("Empty title not allowed!", error.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfTitleIsNull() {
        Optional<ShoppingListError> error = validationRules.emptyTitleRule(null);
        assertTrue(error.isPresent());
    }

    @Test
    public void returnErrorIfTitleIsDuplicate() {
        Mockito.when(repository.findByAccountAndTitle(account, "list1")).thenReturn(Optional.of(shoppingList));
        Optional<ShoppingListError> error = validationRules.duplicateEntryRule(account, "list1");
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("Shopping list with title list1 already in database!", error.get().getErrorDescription());

    }

    @Test
    public void returnNoErrorIfTitleNotDuplicate() {
        Mockito.when(repository.findByAccountAndTitle(account, "list1")).thenReturn(Optional.empty());
        Optional<ShoppingListError> error = validationRules.duplicateEntryRule(account, "list1");
        assertFalse(error.isPresent());
    }
}
