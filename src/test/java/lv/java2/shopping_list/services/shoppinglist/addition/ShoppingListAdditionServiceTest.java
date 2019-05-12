package lv.java2.shopping_list.services.shoppinglist.addition;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListStatus;
import lv.java2.shopping_list.dto.ShoppingListDTO;
import lv.java2.shopping_list.dto.mappers.ShoppingListMapper;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.validation.ShoppingListValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListAdditionServiceTest {

    @Mock
    private ShoppingListRepository repository;
    @Mock
    private ShoppingListMapper mapper;
    @Mock
    private ShoppingListValidationService validator;

    @Captor
    public ArgumentCaptor<ShoppingListDTO> listDTOCaptor;

    private ShoppingListDTO shoppingListDTO = new ShoppingListDTO();
    private ShoppingList shoppingList = new ShoppingList();

    @InjectMocks
    private ShoppingListAdditionService additionService;

    @Before
    public void init() {
        shoppingListDTO.setTitle("title");
        shoppingListDTO.setUserId(1L);
        shoppingListDTO.setStatus(ShoppingListStatus.ACTIVE);
        shoppingListDTO.setId(2L);
        shoppingList.setStatus(ShoppingListStatus.ACTIVE);
        shoppingList.setTitle("title");
        shoppingList.setId(2L);
    }

    @Test
    public void shouldAddNewList() {

        when(mapper.toDomain(shoppingListDTO)).thenReturn(shoppingList);
        when(repository.save(shoppingList)).thenReturn(shoppingList);
        when(mapper.toDTO(shoppingList)).thenReturn(shoppingListDTO);

        ShoppingListDTO result = additionService.addList(shoppingListDTO);
        verify(validator).validate(listDTOCaptor.capture());
        ShoppingListDTO captorResult = listDTOCaptor.getValue();

        assertEquals((long) result.getId(), (long) shoppingListDTO.getId());
        assertThat(result).hasSameClassAs(shoppingListDTO);
        assertThat(result.getStatus()).isEqualTo(ShoppingListStatus.ACTIVE);
        assertThat(captorResult).isEqualTo(shoppingListDTO);

    }
}
