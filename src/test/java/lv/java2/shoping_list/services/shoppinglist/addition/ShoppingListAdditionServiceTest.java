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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListAdditionServiceTest {

    @Mock
    private ShoppingListDBValidator validator;
    @Mock
    private ShoppingListRepository repository;
    @Mock
    private ShoppingListMapper mapper;
    private ShoppingListDTO dto = new ShoppingListDTO();
    private ShoppingList shoppingList= new ShoppingList();

    @InjectMocks
    private ShoppingListAdditionService additionService = new ShoppingListAdditionService();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init(){
        dto.setTitle("title");
        dto.setUserId(1L);
        shoppingList.setStatus(ShoppingListStatus.ACTIVE);
        shoppingList.setId(2L);
    }

    @Test
    public void returnDTOWithIdIfListIsAdded(){
        Mockito.when(mapper.toDomain(dto)).thenReturn(shoppingList);
        Mockito.when(validator.isShoppingListTitleExists(shoppingList.getUser(), dto.getTitle())).thenReturn(false);
        Mockito.when(repository.save(shoppingList)).thenReturn(shoppingList);
        assertNull(dto.getId());
        assertNull(dto.getStatus());
        dto = additionService.addList(dto);
        assertNotNull(dto.getId());
        assertEquals((long) dto.getId(), 2L);
        assertEquals(ShoppingListStatus.ACTIVE, dto.getStatus());
    }


    @Test
    public void throwsExceptionIfShoppingListExists() throws DuplicateResourceException{
        exception.expect(DuplicateResourceException.class);
        exception.expectMessage("Shopping list with title = title already exists!");
        Mockito.when(mapper.toDomain(dto)).thenReturn(shoppingList);
        Mockito.when(validator.isShoppingListTitleExists(shoppingList.getUser(), dto.getTitle())).thenReturn(true);
        dto = additionService.addList(dto);
        assertNull(dto.getId());
    }


}
