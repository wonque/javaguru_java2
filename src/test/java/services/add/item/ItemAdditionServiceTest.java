package services.add.item;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListItem;
import lv.java2.shopping_list.domain.builders.ShoppingListItemBuilder;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.item.ItemAdditionRequest;
import lv.java2.shopping_list.services.add.item.ItemAdditionResponse;
import lv.java2.shopping_list.services.add.item.ItemAdditionService;
import lv.java2.shopping_list.services.add.item.validation.ItemAdditionValidator;
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
    private ShoppingListItemBuilder itemBuilder;

    @InjectMocks
    private ItemAdditionService additionService = new ItemAdditionService();

    private ItemAdditionRequest request;
    private List<ShoppingListError> errors;
    private ShoppingList shoppingList;

    @Before
    public void buildRequestAndErrors() {
        this.shoppingList = new ShoppingList();
        this.request = new ItemAdditionRequest(shoppingList, "testItem");
        this.errors = new ArrayList<>();
    }

    @Test
    public void shouldReturnResponseWithListOfErrors() {
        errors.add(new ShoppingListError("title", "description"));
        System.out.println(errors.size());
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
