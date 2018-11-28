package lv.java2.shopping_list.factories;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListFactory {

    @Autowired
    private ShoppingListRepository repository;

    public ShoppingList saveListToBase(String title) {
        ShoppingList newEntry = createNewShoppingList(title);
        return repository.addToDataBase(newEntry);
    }

    private ShoppingList createNewShoppingList(String title) {
        return new ShoppingList(title);
    }
}
