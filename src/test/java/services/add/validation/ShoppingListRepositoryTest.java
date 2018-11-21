package services.add.validation;

import db.ShoppingListRepository;
import db.jdbc.ShoppingListRepositoryImpl;
import domain.ShoppingList;

import java.util.Optional;

public class ShoppingListRepositoryTest {

    private ShoppingListRepository repository = new ShoppingListRepositoryImpl();


    public void shouldReturnLongIdIfProductIsAdded(){
        ShoppingList shoppingList = new ShoppingList("list1");
        Optional<Long> id = repository.addToDataBase(shoppingList);
    }
}
