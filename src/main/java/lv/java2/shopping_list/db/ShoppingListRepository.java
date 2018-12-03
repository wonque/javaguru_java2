package lv.java2.shopping_list.db;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListItem;

import java.util.Optional;

public interface ShoppingListRepository {

    ShoppingList addToDataBase(ShoppingList shoppingList);

    Optional<ShoppingList> getByTitle(String title);

    boolean remove(ShoppingList shoppingList);

    Optional<ShoppingList> getById(Long id);

    boolean removeListItems (ShoppingList shoppingList);
}
