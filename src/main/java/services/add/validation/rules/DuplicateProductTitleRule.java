package services.add.validation.rules;


import db.ProductRepository;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.Error;

import java.util.Optional;

@Component
public class DuplicateProductTitleRule {

    @Autowired
    private ProductRepository productRepository;

    public DuplicateProductTitleRule(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Error> execute(String title) {
        if (title != null) {
            Optional<Product> product = productRepository.findByTitle(title);
            if (product.isPresent()) {
                Error error = new Error("title", "Product with title " + title + " already in database!");
                return Optional.of(error);
            }
        }
        return Optional.empty();
    }
}
