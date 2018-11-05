package services;

import db.ProductBase;
import domain.Product;

import java.util.List;

public class GetShoppingListService {

    private ProductBase productBase;

    public GetShoppingListService(ProductBase productBase) {
        this.productBase = productBase;
    }

    public List<Product> getList() {
        return productBase.getProductList();
    }
}
