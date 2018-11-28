package lv.java2.shopping_list.services.add.product;

import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.factories.ProductFactory;
import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.add.product.validation.ProductAdditionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductAdditionService {

    @Autowired
    private ProductAdditionValidator validator;

    @Autowired
    private ProductFactory productFactory;

    public ProductAdditionResponse add(ProductAdditionRequest request) {
        List<Error> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ProductAdditionResponse(errors);
        }
        //        newEntry.setListId(request.getShoppingListId());
        Product newEntry = productFactory.saveProductToBase(request.getTitle());

        return new ProductAdditionResponse(newEntry);
    }

}
