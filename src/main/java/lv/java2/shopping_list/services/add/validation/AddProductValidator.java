package lv.java2.shopping_list.services.add.validation;

import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.add.AddProductRequest;

import java.util.List;

public interface AddProductValidator {

    List<Error> validate(AddProductRequest addProductRequest);
}
