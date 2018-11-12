package services;

import db.jdbc.ProductRepositoryImpl;
import domain.Product;

import java.util.Optional;

public class RemoveProductService {

    private ProductRepositoryImpl productRepository;

    public RemoveProductService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    public boolean remove(String title) {
        Optional<Product> foundedProduct = productRepository.findByTitle(title);
        if (foundedProduct.isPresent()) {
            Product productToDelete = foundedProduct.get();
            return productRepository.remove(productToDelete);
        }
        return false;
    }
}
