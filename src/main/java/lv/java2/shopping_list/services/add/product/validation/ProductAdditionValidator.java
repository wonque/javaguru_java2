package lv.java2.shopping_list.services.add.product.validation;

import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.add.product.ProductAdditionRequest;

import java.util.List;

public interface ProductAdditionValidator {

    List<Error> validate(ProductAdditionRequest addProductRequest);

}
