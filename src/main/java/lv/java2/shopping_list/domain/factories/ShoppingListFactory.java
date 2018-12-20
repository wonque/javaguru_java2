package lv.java2.shopping_list.domain.factories;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListStatus;
import org.springframework.beans.factory.annotation.Autowired;
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
