package services;

import db.jdbc.ProductRepositoryImpl;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductDescriptionService {

    @Autowired
    private ProductRepositoryImpl productRepository;

    public ProductDescriptionService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    public boolean update(String productTitle, String description) {
        Optional<Product> foundedProduct = productRepository.findByTitle(productTitle);
        if (foundedProduct.isPresent()) {
            Product product = foundedProduct.get();
            productRepository.updateDescription(product, description);
            return true;
        }
        return false;
    }
}
