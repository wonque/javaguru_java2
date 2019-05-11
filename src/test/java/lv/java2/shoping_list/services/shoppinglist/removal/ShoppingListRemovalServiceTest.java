package lv.java2.shoping_list.services.shoppinglist.removal;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.dto.ShoppingListDTO;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.removal.ShoppingListRemovalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ShoppingListRemovalServiceTest {

    @Mock
    private ShoppingListRepository repository;
    @InjectMocks
    public ShoppingListRemovalService removalService;

    private ShoppingListDTO dto = new ShoppingListDTO();
    private ShoppingList shoppingList = new ShoppingList();

    @Captor
    public ArgumentCaptor<ShoppingList> listCaptor;

    @Before
    public void setup() {
        dto.setUserId(1L);
        dto.setId(1L);
    }

    @Test
    public void test1() {

        when(repository.findByUserIdAndListId(1L, 1L)).thenReturn(Optional.of(shoppingList));

        removalService.removeById(dto);

        verify(repository).delete(listCaptor.capture());
        ShoppingList captorResult = listCaptor.getValue();

        assertThat(captorResult).isEqualTo(shoppingList);

    }


}
