package lv.java2.shopping_list.domain.factories;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListItem;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListItemFactory {


    public ShoppingListItem createWithTitle (ShoppingList shoppingList, String title){
        ShoppingListItem item = new ShoppingListItem();
        item.setShoppingList(shoppingList);
        item.setTitle(title);
        return item;
    }

}
