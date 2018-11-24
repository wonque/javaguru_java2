package lv.java2.shopping_list.db;

import lv.java2.shopping_list.domain.ShoppingList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryShoppingListRepository implements ShoppingListRepository {

    private List<ShoppingList> lists = new ArrayList<>();

    @Override
    public Optional<Long> addToDataBase(ShoppingList shoppingList) {
        Long id = (long) lists.size() + 1;
        lists.add(shoppingList);
        return Optional.of(id);
    }
}
