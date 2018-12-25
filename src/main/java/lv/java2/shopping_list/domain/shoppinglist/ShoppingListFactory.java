package lv.java2.shopping_list.domain.shoppinglist;

import lv.java2.shopping_list.domain.account.Account;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListFactory {

    public ShoppingList createInstance(Account account, String title) {
        ShoppingList newList = new ShoppingList();
        newList.setAccount(account);
        newList.setTitle(title);
        newList.setStatus(ShoppingListStatus.ACTIVE);
        return newList;
    }
}
