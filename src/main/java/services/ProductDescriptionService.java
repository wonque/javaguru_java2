package services;

import db.jdbc.ProductRepositoryImpl;
import domain.Product;

import java.util.Optional;

public class ProductDescriptionService {

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
