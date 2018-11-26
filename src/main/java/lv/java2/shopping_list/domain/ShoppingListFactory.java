package lv.java2.shopping_list.domain;

import org.springframework.stereotype.Component;

@Component
public class ShoppingListFactory {

    public ShoppingList createNewShoppingList(String title) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setTitle(title);
        return shoppingList;
    }
}
