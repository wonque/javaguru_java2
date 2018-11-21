package db;

import domain.ShoppingList;

import java.util.Optional;

public interface ShoppingListRepository {

    Optional<Long> addToDataBase(ShoppingList shoppingList);
}
