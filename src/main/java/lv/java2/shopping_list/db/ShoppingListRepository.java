package lv.java2.shopping_list.db;

import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.domain.item.ShoppingListItem;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository {

    ShoppingList addToDataBase(ShoppingList shoppingList);

    Optional<ShoppingList> findByAccountAndTitle(Account account, String title);

    boolean remove(ShoppingList shoppingList);

    List<ShoppingListItem> getShoppingList (ShoppingList shoppingList);

}
