package lv.java2.shopping_list.services.remove;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.java2.shopping_list.services.Error;

import java.util.Optional;

@Component
public class RemoveProductRules {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Error> nullTitleRule(String title) {
        if (title == null) {
            Error error = new Error("title", "Product title cannot be empty!");
            return Optional.of(error);
        }
        return Optional.empty();
    }

    public Optional<Error> productPresenceInDataBaseRule(String title) {
        Optional<Product> foundedProduct = productRepository.findByTitle(title);
        if (!foundedProduct.isPresent()) {
            Error error = new Error("title",
                    String.format("Product with title '%s' not in a database\n", title.toUpperCase()));
            return Optional.of(error);
        } else {
            return Optional.empty();
        }

    }
}
