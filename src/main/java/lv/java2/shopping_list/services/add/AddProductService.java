package lv.java2.shopping_list.services.add;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.add.validation.AddProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddProductValidator validator;


    public AddProductResponse add(AddProductRequest request) {
        List<Error> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }

        Product newEntry = new Product((request.getTitle()));

        productRepository.addToDataBase(newEntry);

        return new AddProductResponse(newEntry.getId());
    }
}
