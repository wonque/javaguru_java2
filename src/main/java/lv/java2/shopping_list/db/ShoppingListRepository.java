package lv.java2.shopping_list.db;

import lv.java2.shopping_list.domain.ShoppingList;

import java.util.Optional;

public interface ShoppingListRepository {

    Optional<Long> addToDataBase(ShoppingList shoppingList);
}
