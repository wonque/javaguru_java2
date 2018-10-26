package services;

import db.Database;
import domain.Product;

import java.util.List;

public class GetShoppingListService {

    private Database database;

    public GetShoppingListService(Database database) {
        this.database = database;
    }

    public List<Product> getList() {
        return database.getProductList();
    }
}
