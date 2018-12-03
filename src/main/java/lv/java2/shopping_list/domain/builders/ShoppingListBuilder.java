package lv.java2.shopping_list.domain.builders;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListBuilder {

    @Autowired
    private ShoppingListRepository repository;


    public ShoppingList createWithTitle (String title) {
        return new ShoppingList(title);
    }
}
