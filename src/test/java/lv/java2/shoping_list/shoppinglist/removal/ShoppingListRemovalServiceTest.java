package lv.java2.shoping_list.shoppinglist.removal;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListStatus;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.removal.ShoppingListRemovalService;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import org.junit.Test;
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

    @InjectMocks
    private ShoppingListRemovalService removalService = new ShoppingListRemovalService();

    @Test
    public void returnDtoWithDeletedStatusWhenRemoveById(){
        Mockito.when(repository.findByUserIdAndListId(1L, 1L)).thenReturn(Optional.of(shoppingList));
        ShoppingListDTO dto = removalService.removeById(1L, 1L);
        assertEquals(ShoppingListStatus.DELETED, dto.getStatus());
    }

    @Test
    public void returnDtoWithDeletedStatusWhenRemovedByTitle(){
        Mockito.when(repository.findByTitle("title")).thenReturn(Optional.of(shoppingList));
        ShoppingListDTO dto = removalService.removeByTitle("title");
        assertEquals(ShoppingListStatus.DELETED, dto.getStatus());
    }

}
