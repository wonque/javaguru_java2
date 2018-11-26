package lv.java2.shopping_list.services.add.validation.rules;


import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.ErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DuplicateProductTitleRule {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ErrorFactory errorFactory;

    public DuplicateProductTitleRule(ProductRepository productSearchRepository) {
        this.productRepository = productSearchRepository;
    }

    public Optional<Error> execute(String title) {
        if (title != null) {
            Optional<Product> product = productRepository.findByTitle(title);
            if (product.isPresent()) {
                Error error = errorFactory.createNewError("title", "Product with title " + title + " already in database!");
                return Optional.of(error);
            }
        }
        return Optional.empty();
    }
}