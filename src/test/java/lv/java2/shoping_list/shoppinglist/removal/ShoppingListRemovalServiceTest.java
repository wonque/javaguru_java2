package lv.java2.shoping_list.shoppinglist.removal;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListStatus;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.removal.ShoppingListRemovalService;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.exceptions.ResourceNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ShoppingListRemovalServiceTest {

    @Mock
    private ShoppingListRepository repository;
    private ShoppingList shoppingList = new ShoppingList();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private ShoppingListRemovalService removalService = new ShoppingListRemovalService();

    @Test
    public void returnDtoWithDeletedStatusWhenRemoveById() {
        Mockito.when(repository.findByUserIdAndListId(1L, 1L)).thenReturn(Optional.of(shoppingList));
        ShoppingListDTO dto = removalService.removeById(1L, 1L);
        assertEquals(ShoppingListStatus.DELETED, dto.getStatus());
    }

    @Test
    public void returnDtoWithDeletedStatusWhenRemovedByTitle() {
        Mockito.when(repository.findByTitle("title")).thenReturn(Optional.of(shoppingList));
        ShoppingListDTO dto = removalService.removeByTitle("title");
        assertEquals(ShoppingListStatus.DELETED, dto.getStatus());
    }

    @Test
    public void throwsExceptionWhenIDNotFound() {
        exception.expect(ResourceNotFoundException.class);
        exception.expectMessage("Shopping list with ID = 1 not found!");
        Mockito.when(repository.findByUserIdAndListId(1L, 1L)).thenReturn(Optional.empty());
        ShoppingListDTO dto = removalService.removeById(1L, 1L);
    }

    @Test
    public void throwsExceptionWhenTitleNotFound() {
        exception.expect(ResourceNotFoundException.class);
        exception.expectMessage("Shopping list with TITLE = list not found!");
        Mockito.when(repository.findByTitle("list")).thenReturn(Optional.empty());
        ShoppingListDTO dto = removalService.removeByTitle("list");
    }

    @Test
    public void throwsExceptionWhenIDsAreNull() {
        exception.expect(ResourceNotFoundException.class);
        exception.expectMessage("Shopping list with ID = null not found!");
        ShoppingListDTO dto = removalService.removeById(null, null);
    }

    @Test
    public void throwsExceptionWhenTitleIsNull() {
        exception.expect(ResourceNotFoundException.class);
        exception.expectMessage("Shopping list with TITLE = null not found!");
        ShoppingListDTO dto = removalService.removeByTitle(null);
    }


}
