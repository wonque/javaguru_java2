package lv.java2.shoping_list.services.shoppinglist.addition;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListStatus;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListDBValidator;
import lv.java2.shopping_list.services.shoppinglist.addition.ShoppingListAdditionService;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.dto.mappers.ShoppingListMapper;
import lv.java2.shopping_list.web.exceptions.DuplicateResourceException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListAdditionServiceTest {

    @Mock
    private ShoppingListRepository repository;
    @Mock
    private ShoppingListMapper mapper;
    @Mock
    private ShoppingListDBValidator validator;

    private ShoppingListDTO dto = new ShoppingListDTO();
    private ShoppingList shoppingList = new ShoppingList();

    @InjectMocks
    private ShoppingListAdditionService additionService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        dto.setTitle("title");
        dto.setUserId(1L);
        shoppingList.setStatus(ShoppingListStatus.ACTIVE);
        shoppingList.setId(2L);
    }

    @Test
    public void returnDTOWithIdIfListIsAdded() {
        when(mapper.toDomain(dto)).thenReturn(shoppingList);
        when(repository.save(shoppingList)).thenReturn(shoppingList);
        assertNull(dto.getId());
        assertNull(dto.getStatus());
        dto = additionService.addList(dto);
        assertNotNull(dto.getId());
        assertEquals((long) dto.getId(), 2L);
        assertEquals(ShoppingListStatus.ACTIVE, dto.getStatus());
    }
}
