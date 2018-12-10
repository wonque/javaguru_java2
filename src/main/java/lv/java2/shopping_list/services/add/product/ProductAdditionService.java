package lv.java2.shopping_list.services.add.product;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.domain.builders.ProductBuilder;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.product.validation.ProductAdditionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class ProductAdditionService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductAdditionValidator validator;

    @Autowired
    private ProductBuilder productBuilder;

    public ProductAdditionResponse add(ProductAdditionRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ProductAdditionResponse(errors);
        }
        Product newEntry = productBuilder.buildInstance(request.getTitle());
        repository.addToDataBase(newEntry);
//        System.out.println(newEntry);
        return new ProductAdditionResponse(newEntry.getId());
    }

}
