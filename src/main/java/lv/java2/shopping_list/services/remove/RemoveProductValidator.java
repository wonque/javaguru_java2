package lv.java2.shopping_list.services.remove;

import lv.java2.shopping_list.services.Error;

import java.util.List;

public interface RemoveProductValidator {

    List<Error> validate(RemoveProductRequest request);

}
