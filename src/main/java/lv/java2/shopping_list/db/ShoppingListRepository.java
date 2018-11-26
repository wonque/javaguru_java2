package lv.java2.shopping_list.db;

import lv.java2.shopping_list.domain.ShoppingList;

import java.util.Optional;

public interface ShoppingListRepository {

    ShoppingList addToDataBase(ShoppingList shoppingList);

    Optional<ShoppingList> getByTitle (String title);

    boolean isInDataBase(String title);
}
