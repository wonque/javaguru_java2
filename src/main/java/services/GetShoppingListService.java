package services;

import db.jdbc.ProductRepositoryImpl;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetShoppingListService {

    @Autowired
    private ProductRepositoryImpl productRepository;

    public GetShoppingListService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getList() {
        return productRepository.getProductList();
    }
}
