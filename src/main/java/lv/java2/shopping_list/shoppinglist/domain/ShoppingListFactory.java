package lv.java2.shopping_list.shoppinglist.domain;

import lv.java2.shopping_list.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListFactory {

    public ShoppingList createInstance(User user, String title) {
        ShoppingList newList = new ShoppingList();
        newList.setUser(user);
        newList.setTitle(title);
        newList.setStatus(ShoppingListStatus.ACTIVE);
        return newList;
    }

}
