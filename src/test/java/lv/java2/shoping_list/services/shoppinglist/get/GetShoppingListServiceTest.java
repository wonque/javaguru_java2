package lv.java2.shoping_list.services.shoppinglist.get;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListDBValidator;
import lv.java2.shopping_list.services.shoppinglist.get.GetShoppingListService;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.dto.mappers.ShoppingListMapper;
import lv.java2.shopping_list.web.exceptions.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GetShoppingListServiceTest {

    @Mock
    private ShoppingListRepository repository;
    @Mock
    private ShoppingListDBValidator validator;
    @Mock
    private ShoppingListMapper mapper;
    private List<ShoppingList> allLists = new ArrayList<>();
    private ShoppingList list1 = new ShoppingList();
    private ShoppingList list2 = new ShoppingList();
    private ShoppingListDTO dto1 = new ShoppingListDTO();
    private ShoppingListDTO dto2 = new ShoppingListDTO();

    @InjectMocks
    private GetShoppingListService getService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        list1.setId(1L);
        list2.setId(2L);
        list1.setTitle("list1");
        list2.setTitle("list2");
        allLists.add(list1);
        allLists.add(list2);
        dto1.setId(6L);
        dto2.setId(7L);
        dto1.setTitle("DTO-ODIN");
        dto2.setTitle("DTO-NEODIN");
    }


    @Test
    public void returnListWithDTOsOfUsersShoppingLists() {
        Mockito.when(validator.isUserExists(1L)).thenReturn(true);
        Mockito.when(repository.findAllByUserId(1L)).thenReturn(allLists);
        Mockito.when(mapper.toDTO(list1)).thenReturn(dto1);
        Mockito.when(mapper.toDTO(list2)).thenReturn(dto2);
        List<ShoppingListDTO> response = getService.getAllByUserId(1L);
        assertEquals(2, response.size());
    }

    @Test
    public void returnSingleListByUserIdAndListId(){
        Mockito.when(validator.isUserExists(1L)).thenReturn(true);
        Mockito.when(repository.findByUserIdAndListId(1L, 1L)).thenReturn(Optional.of(list1));
        Mockito.when(mapper.toDTO(list1)).thenReturn(dto1);
        ShoppingListDTO otherDTO = getService.getSingleById(1L, 1L);
        assertNotNull(otherDTO.getId());
        assertEquals((long)otherDTO.getId(), 6L);
        assertEquals("DTO-ODIN", otherDTO.getTitle());
    }

    @Test
    public void throwsExceptionIfUserIdNotExists() throws ResourceNotFoundException{
        Mockito.when(validator.isUserExists(1L)).thenReturn(false);
        exception.expect(ResourceNotFoundException.class);
        exception.expectMessage("Unable to find user with ID = 1");
        ShoppingListDTO otherDTO = getService.getSingleById(1L, 1L);
    }

    @Test
    public void throwsExceptionIfShoppingListIdNotExists() throws ResourceNotFoundException{
        Mockito.when(validator.isUserExists(1L)).thenReturn(true);
        Mockito.when(repository.findByUserIdAndListId(1L, 1L)).thenReturn(Optional.empty());
        exception.expect(ResourceNotFoundException.class);
        exception.expectMessage("ShoppingList with ID = 1 not found!");
        ShoppingListDTO otherDTO = getService.getSingleById(1L, 1L);

    }
}
