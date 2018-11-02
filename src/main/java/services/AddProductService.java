package services;

import db.Database;
import domain.Product;

public class AddProductService {

    private Database database;

    public AddProductService(Database database) {
        this.database = database;
    }

    public void add(Product newEntry) {
        database.add(newEntry);
    }

    public Product createNewProduct() {
        return new Product();
    }

}
