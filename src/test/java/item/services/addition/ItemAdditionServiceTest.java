package item.services.addition;

import lv.java2.shopping_list.item.repository.ItemRepository;
import lv.java2.shopping_list.item.domain.Item;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.item.domain.ItemFactory;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.item.services.addition.ItemAdditionResponse;
import lv.java2.shopping_list.item.services.addition.ItemAdditionService;
import lv.java2.shopping_list.item.services.addition.validation.ItemAdditionValidator;
import lv.java2.shopping_list.item.services.ItemAddRemoveSharedRequest;
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
    private ItemRepository repository;

    @Mock
    private ItemAdditionValidator validator;

    @Mock
    private ItemFactory itemBuilder;

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
        Item item = Mockito.mock(Item.class);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(itemBuilder.createWithTitle(shoppingList,"testItem")).thenReturn(item);
        Mockito.when(repository.addItemToShoppingList(item)).thenReturn(item);
        ItemAdditionResponse response = additionService.addItem(request);
        assertTrue(response.isSuccess());
        assertNotNull(response.getItem());
    }


}
