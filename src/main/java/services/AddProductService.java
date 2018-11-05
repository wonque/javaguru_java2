package services;

import db.ProductBase;
import domain.Product;

public class AddProductService {

    private ProductBase productBase;

    public AddProductService(ProductBase productBase) {
        this.productBase = productBase;
    }

    public void add(Product newEntry) {
        productBase.addToDataBase(newEntry);
    }

    public Product createNewProduct(String title) {
        Product newEntry = new Product();
        newEntry.setTitle(title);
        return newEntry;
    }

    public boolean isProductTitleEmpty(String title){
        return title == null || title.isEmpty() || "\"\"".equals(title)
                || "\"".equals(title);
    }

}
