package services;

import db.jdbc.ProductRepositoryImpl;
import domain.Product;

import java.util.List;

public class GetShoppingListService {

    private ProductRepositoryImpl productRepository;

    public GetShoppingListService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getList() {
        return productRepository.getProductList();
    }
}
