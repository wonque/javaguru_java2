package services.add.validation;

import db.ShoppingListRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import services.AddShoppingListService;

@RunWith(MockitoJUnitRunner.class)
public class AddShoppingListServiceTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;


    @InjectMocks
    private AddShoppingListService addShoppingListService = new AddShoppingListService(shoppingListRepository);


    @Test
    public void shouldReturnLongIdWhenListIsAddedToDataBase(){}
}
