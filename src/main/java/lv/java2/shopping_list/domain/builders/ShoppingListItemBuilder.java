package lv.java2.shopping_list.domain.builders;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListItem;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListItemBuilder {


    public ShoppingListItem createWithTitle (ShoppingList shoppingList, String title){
        ShoppingListItem item = new ShoppingListItem();
        item.setShoppingList(shoppingList);
        item.setTitle(title);
        return item;
    }

}
