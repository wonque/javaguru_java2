package services;

import db.ProductBase;
import domain.Product;

import java.util.Optional;

public class RemoveProductService {

    private ProductBase productBase;

    public RemoveProductService(ProductBase productBase) {
        this.productBase = productBase;
    }

    public boolean remove(String title) {
        Optional<Product> foundedProduct = productBase.findByTitle(title);
        if (foundedProduct.isPresent()) {
            Product productToDelete = foundedProduct.get();
            return productBase.remove(productToDelete);
        }
        return false;
    }
}
