package lv.java2.shoping_list.services.shoppinglist.get;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.get.GetShoppingListService;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.dto.mappers.ShoppingListMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetShoppingListServiceTest {

    @Mock
    private ShoppingListRepository repository;
    @Mock
    private ShoppingListMapper mapper;

    private List<ShoppingListDTO> dtoList;
    private List<ShoppingList> shoppingLists;
    private ShoppingListDTO listDTO;
    private ShoppingList list;

    @InjectMocks
    private GetShoppingListService getService;

    @Before
    public void setup() {
        this.dtoList = new ArrayList<>();
        this.listDTO = new ShoppingListDTO();
        this.list = new ShoppingList();
        this.shoppingLists = new ArrayList<>();
    }


    @Test
    public void shouldReturnListWithDTO() {

        fillShoppingList();
        when(repository.findAllByUserId(1L)).thenReturn(shoppingLists);
        when(mapper.toDTO(list)).thenReturn(listDTO);

        dtoList = getService.getAllByUserId(1L);

        assertThat(dtoList.size()).isEqualTo(4);
    }

    @Test
    public void shouldReturnSingleListDTO() {

        when(repository.findByUserIdAndListId(1L, 1L)).thenReturn(Optional.of(list));
        when(mapper.toDTO(list)).thenReturn(listDTO);

        ShoppingListDTO result = getService.getSingleById(1L, 1L);
        assertThat(result).isEqualTo(listDTO);

    }

    @Test
    public void shoudThrowExceptionWhenListNotFound() {

        when(repository.findByUserIdAndListId(1L, 1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> getService.getSingleById(1L, 1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("ShoppingList with ID = 1 not found!");

    }

    private void fillShoppingList() {
        for (int i = 0; i <= 3; i++) {
            shoppingLists.add(new ShoppingList());
        }
    }


}
