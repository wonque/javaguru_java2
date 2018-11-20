package services;

import db.jdbc.ShoppingListRepositoryImpl;

public class AddShoppingListService {

    private ShoppingListRepositoryImpl database;

    public AddShoppingListService(ShoppingListRepositoryImpl database) {
        this.database = database;
    }


}
