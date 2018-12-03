package lv.java2.shopping_list.services.add.product.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.product.ProductAdditionRequest;

import java.util.List;

public interface ProductAdditionValidator {

    List<ShoppingListError> validate(ProductAdditionRequest addProductRequest);

}
