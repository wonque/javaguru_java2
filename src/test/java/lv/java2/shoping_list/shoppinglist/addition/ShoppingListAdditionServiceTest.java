package lv.java2.shoping_list.shoppinglist.addition;

import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListDBValidator;
import lv.java2.shopping_list.services.shoppinglist.addition.ShoppingListAdditionService;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListAdditionServiceTest {

    @Mock
    private ShoppingListDBValidator validator;

    @Mock
    private ShoppingListRepository repository;

    @InjectMocks
    private ShoppingListAdditionService additionService = new ShoppingListAdditionService();

    @Rule
    public ExpectedException exception = ExpectedException.none();


}
