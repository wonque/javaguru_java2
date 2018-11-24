package lv.java2.shopping_list.services;

import lv.java2.shopping_list.db.ShoppingListRepository;

public class AddShoppingListService {

    private ShoppingListRepository database;

    public AddShoppingListService(ShoppingListRepository database) {
        this.database = database;
    }

    public void addList() {
    }

}
