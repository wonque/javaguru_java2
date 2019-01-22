package lv.java2.shopping_list.item.domain;

import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import org.springframework.stereotype.Component;

@Component
public class ItemFactory {


    public Item createWithTitle (ShoppingList shoppingList, String title){
        Item item = new Item();
        item.setShoppingList(shoppingList);
        item.setTitle(title);
        return item;
    }

}
