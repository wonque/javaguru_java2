package db;

import domain.ShoppingList;

public interface ShoppingListRepository {

    void addToDataBase(ShoppingList shoppingList);
}
