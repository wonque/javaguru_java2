package services;

import db.jdbc.ProductRepositoryImpl;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RemoveProductService {

    @Autowired
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
