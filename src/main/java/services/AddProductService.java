package services;

import db.ProductRepository;
import db.jdbc.ProductRepositoryImpl;
import domain.Product;

public class AddProductService {

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
