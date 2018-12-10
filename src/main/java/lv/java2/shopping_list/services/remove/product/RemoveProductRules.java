package lv.java2.shopping_list.services.remove.product;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
public class RemoveProductRules {

    @Autowired
    private ProductRepository productRepository;

    public Optional<ShoppingListError> nullTitleRule(String title) {
        if (title == null) {
            ShoppingListError error = new ShoppingListError("title", "Product title cannot be empty!");
            return Optional.of(error);
        }
        return Optional.empty();
    }

    public Optional<ShoppingListError> productPresenceInDataBaseRule(String title) {
        Optional<Product> foundedProduct = productRepository.findByTitle(title);
        if (!foundedProduct.isPresent()) {
            ShoppingListError error = new ShoppingListError("title",
                    String.format("Product with title '%s' not in a database\n", title.toUpperCase()));
            return Optional.of(error);
        } else {
            return Optional.empty();
        }

    }
}
