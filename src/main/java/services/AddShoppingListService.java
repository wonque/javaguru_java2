package services;

import db.ShoppingListRepository;

public class AddShoppingListService {

    private ShoppingListRepository database;

    public AddShoppingListService(ShoppingListRepository database) {
        this.database = database;
    }

    public void addList() {
    }

}
