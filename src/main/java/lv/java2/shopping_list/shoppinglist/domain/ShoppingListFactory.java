package lv.java2.shopping_list.shoppinglist.domain;

import lv.java2.shopping_list.account.domain.Account;
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
