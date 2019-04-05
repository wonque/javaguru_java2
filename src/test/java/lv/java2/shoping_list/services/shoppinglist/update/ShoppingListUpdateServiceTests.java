package lv.java2.shoping_list.services.shoppinglist.update;

import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListDBValidator;
import lv.java2.shopping_list.services.shoppinglist.update.ShoppingListUpdateService;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.dto.mappers.ShoppingListMapper;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListUpdateServiceTests {

    @Mock
    private ShoppingListRepository repository;
    @Mock
    private ShoppingListDBValidator validator;
    @Mock
    private ShoppingListMapper mapper;

    @InjectMocks
    private ShoppingListUpdateService updateService = new ShoppingListUpdateService();
    private ShoppingListDTO requestDTO = new ShoppingListDTO(1L, "title");
}
