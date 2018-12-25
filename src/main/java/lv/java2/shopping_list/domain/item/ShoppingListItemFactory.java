package lv.java2.shopping_list.domain.item;

import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.domain.item.ShoppingListItem;
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
