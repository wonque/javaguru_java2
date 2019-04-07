package lv.java2.shoping_list.services.shoppinglist.addition;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListStatus;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListDBValidator;
import lv.java2.shopping_list.services.shoppinglist.addition.ShoppingListAdditionService;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.dto.mappers.ShoppingListMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListAdditionServiceTest {

    @Mock
    private ShoppingListRepository repository;
    @Mock
    private ShoppingListMapper mapper;
    @Mock
    private ShoppingListDBValidator validator;

    private ShoppingListDTO requestDto = new ShoppingListDTO();
    private ShoppingListDTO responseDto = new ShoppingListDTO();
    private ShoppingList shoppingList = new ShoppingList();

    @InjectMocks
    private ShoppingListAdditionService additionService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        requestDto.setTitle("title");
        requestDto.setUserId(1L);
        responseDto.setId(2L);
        responseDto.setStatus(ShoppingListStatus.ACTIVE);
        shoppingList.setStatus(ShoppingListStatus.ACTIVE);
        shoppingList.setId(2L);
    }

    @Test
    public void returnDTOWithIdIfListIsAdded() {
        when(mapper.toDomain(requestDto)).thenReturn(shoppingList);
        when(repository.save(shoppingList)).thenReturn(shoppingList);
        when(mapper.toDTO(shoppingList)).thenReturn(responseDto);
        responseDto = additionService.addList(requestDto);
        assertNotNull(responseDto.getId());
        assertEquals((long) responseDto.getId(), 2L);
        assertEquals(ShoppingListStatus.ACTIVE, responseDto.getStatus());
    }
}
