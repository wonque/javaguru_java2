package services;

import db.jdbc.ProductRepositoryImpl;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddProductService {

    @Autowired
    private ProductRepositoryImpl productRepository;

    public AddProductService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    public void add(String title) {
        Product newEntry = new Product();
        newEntry.setTitle(title);
        productRepository.addToDataBase(newEntry);
    }

}
