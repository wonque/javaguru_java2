package services.remove;

import db.ProductRepository;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.Error;

import java.util.Optional;

@Component
public class RemoveProductRules {

    @Autowired
    private ProductRepository repository;

    public Optional<Error> emptyTitleRule(String title) {
        if (title == null || title.isEmpty() || title.matches("\\s+")) {
            Error error = new Error("title", "Product title cannot be empty!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Error> isProductInDataBaseRule(String title) {
        Optional<Product> foundedProduct = repository.findByTitle(title);
        if (!foundedProduct.isPresent()) {
            Error error = new Error("title", String.format("Product with title '%s' not in a database", title.toUpperCase()));
            return Optional.of(error);
        } else {
            return Optional.empty();
        }

    }
}
