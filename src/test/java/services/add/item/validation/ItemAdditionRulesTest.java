package services.add.item.validation;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListItem;
import lv.java2.shopping_list.domain.builders.ShoppingListItemBuilder;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.item.validation.ItemAdditionRules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
@RunWith(MockitoJUnitRunner.class)
public class ItemAdditionRulesTest {

    @Mock
    private ShoppingListItemRepository itemRepository;

    @InjectMocks
    private ItemAdditionRules rules = new ItemAdditionRules();

    private ShoppingListItemBuilder itemBuilder = new ShoppingListItemBuilder();


    @Test
    public void returnErrorIfTitleIsEmptyOrWhitespace() {
        Optional<ShoppingListError> emptyTitle = rules.emptyTitle("");
        Optional<ShoppingListError> whiteSpace = rules.emptyTitle("   ");
        assertTrue(emptyTitle.isPresent());
        assertTrue(whiteSpace.isPresent());
        assertEquals("title", emptyTitle.get().getField());
        assertEquals("Empty title not allowed!", emptyTitle.get().getErrorDescription());
        assertEquals("title", whiteSpace.get().getField());
        assertEquals("Empty title not allowed!", whiteSpace.get().getErrorDescription());
    }

    @Test
    public void returnErrorIfItemIsInDatabase() {
        ShoppingList shoppingList = new ShoppingList();
        ShoppingListItem item = itemBuilder.createWithTitle(shoppingList, "milk");

        Mockito.when(itemRepository.findItemByTitle(shoppingList,"milk")).thenReturn(Optional.of(item));
        Optional<ShoppingListError> error = rules.duplicateItem(shoppingList, "milk");

        assertTrue(error.isPresent());
        assertEquals("title", error.get().getField());
        assertEquals("Item already in list!", error.get().getErrorDescription());
    }

}
