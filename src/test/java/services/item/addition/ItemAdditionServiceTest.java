package services.item.addition;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListItem;
import lv.java2.shopping_list.domain.factories.ShoppingListItemFactory;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.addition.ItemAdditionResponse;
import lv.java2.shopping_list.services.item.addition.ItemAdditionService;
import lv.java2.shopping_list.services.item.addition.validation.ItemAdditionValidator;
import lv.java2.shopping_list.services.item.ItemAddRemoveSharedRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemAdditionServiceTest {

    @Mock
    private ShoppingListItemRepository repository;

    @Mock
    private ItemAdditionValidator validator;

    @Mock
    private ShoppingListItemFactory itemBuilder;

    @InjectMocks
    private ItemAdditionService additionService = new ItemAdditionService();

    private ItemAddRemoveSharedRequest request;
    private List<ShoppingListError> errors;
    private ShoppingList shoppingList;

    @Before
    public void buildRequestAndErrors() {
        this.shoppingList = new ShoppingList();
        this.request = new ItemAddRemoveSharedRequest(shoppingList, "testItem");
        this.errors = new ArrayList<>();
    }

    @Test
    public void shouldReturnResponseWithListOfErrors() {
        errors.add(new ShoppingListError("title", "description"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ItemAdditionResponse response = additionService.addItem(request);
        assertFalse(response.isSuccess());
        assertEquals(1, response.getErrorList().size());
        Mockito.verifyZeroInteractions(repository);
    }

    @Test
    public void shouldReturnResponseWithAccount() {
        ShoppingListItem shoppingListItem = Mockito.mock(ShoppingListItem.class);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(itemBuilder.createWithTitle(shoppingList,"testItem")).thenReturn(shoppingListItem);
        Mockito.when(repository.addItemToShoppingList(shoppingListItem)).thenReturn(shoppingListItem);
        ItemAdditionResponse response = additionService.addItem(request);
        assertTrue(response.isSuccess());
        assertNotNull(response.getShoppingListItem());
    }


}
