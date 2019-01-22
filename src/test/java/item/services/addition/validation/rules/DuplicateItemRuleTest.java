package item.services.addition.validation.rules;

import lv.java2.shopping_list.item.repository.ItemRepository;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.item.domain.Item;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.item.services.addition.validation.rules.DuplicateItemRule;
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
public class DuplicateItemRuleTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ShoppingList shoppingList;

    @InjectMocks
    private DuplicateItemRule duplicateItemRule = new DuplicateItemRule();

    private Item item = new Item();



    @Test
    public void returnErrorIfItemAlreadyInDatabase() {
        Mockito.when(itemRepository.findItemByTitle(shoppingList, "title"))
                .thenReturn(Optional.of(item));
        Optional<ShoppingListError> error = duplicateItemRule.execute(shoppingList, "title");
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("Item already in list!", error.get().getErrorDescription());
    }

    @Test
    public void returnNoErrorIfItemNotInDatabase(){
        Mockito.when(itemRepository.findItemByTitle(shoppingList, "title"))
                .thenReturn(Optional.empty());
        Optional<ShoppingListError> error = duplicateItemRule.execute(shoppingList, "title");
        assertFalse(error.isPresent());
    }
}
