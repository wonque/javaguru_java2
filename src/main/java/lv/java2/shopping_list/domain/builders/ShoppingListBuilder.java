package lv.java2.shopping_list.domain.builders;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListBuilder {


    public ShoppingList createInstance(Account account, String title) {
        ShoppingList newList = new ShoppingList();
        newList.setAccount(account);
        newList.setTitle(title);
        return newList;
    }
}
